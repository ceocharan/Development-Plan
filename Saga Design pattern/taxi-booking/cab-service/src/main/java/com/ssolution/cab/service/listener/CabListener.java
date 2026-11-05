package com.ssolution.cab.service.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssolution.cab.service.entity.Cab;
import com.ssolution.cab.service.service.CabService;
import com.ssolution.dtos.cab.events.CabEvent;
import com.ssolution.dtos.driver.events.UpdateDriverStatusEvent;
import com.ssolution.dtos.enums.CommonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CabListener {
    private static final String UPDATE_DRIVER_EVENT = "update_driver_event";
    private static final String ADD_CAB_EVENTS = "add_cab_event";
    @Autowired
    private CabService cabService;
    @Autowired
    private KafkaTemplate<String, UpdateDriverStatusEvent> updateDriverStatusEventKafkaTemplate;

    @KafkaListener(topics = ADD_CAB_EVENTS, groupId = "driver-group")
    public void addCabEvent(String event) throws JsonProcessingException {
        CabEvent cabEvent = new ObjectMapper().readValue(event, CabEvent.class);
        boolean cabExists = cabService.existsByRegistrationNumberAndCabStatus(cabEvent.getRegistrationNumber(), CommonStatus.SUCCESS);
        CommonStatus status = CommonStatus.SUCCESS;
        if(cabExists) {
            // update the driver details to failed status
            status = CommonStatus.FAILED;
        }

        saveCabDetailsAndUpdateDriverEvent(cabEvent, status);
    }

    private void saveCabDetailsAndUpdateDriverEvent(CabEvent cabEvent, CommonStatus commonStatus) {
        Cab cab = saveCabDetails(cabEvent, commonStatus);
        updateDriverEvent(cab, commonStatus);
    }

    private  Cab saveCabDetails(CabEvent cabEvent, CommonStatus cabStatus) {
        return cabService.saveCab(Cab.builder().driverId(cabEvent.getDriverId())
                .registrationNumber(cabEvent.getRegistrationNumber())
                .cabType(cabEvent.getCabType()).status(cabStatus)
                .build());

    }

    private void updateDriverEvent(Cab cab, CommonStatus driverStatus) {
        UpdateDriverStatusEvent  statusEvent = UpdateDriverStatusEvent.builder().driverId(cab.getDriverId()).driverStatus(driverStatus).build();
        updateDriverStatusEventKafkaTemplate.send(UPDATE_DRIVER_EVENT, statusEvent);
    }
}
