package com.ssolution.driver.service.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssolution.driver.service.service.DriverService;
import com.ssolution.dtos.driver.events.UpdateDriverStatusEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DriverListener {
    private static final String UPDATE_DRIVER_EVENT = "update_driver_event";
    @Autowired
    private DriverService driverService;
    @KafkaListener(topics = UPDATE_DRIVER_EVENT, groupId = "cab-group")
    public void updateDriverEvent(String event) throws JsonProcessingException {
        UpdateDriverStatusEvent statusEvent = new ObjectMapper().readValue(event, UpdateDriverStatusEvent.class);
        driverService.updateDriverStatus(statusEvent.getDriverId(), statusEvent.getDriverStatus());
    }

}
