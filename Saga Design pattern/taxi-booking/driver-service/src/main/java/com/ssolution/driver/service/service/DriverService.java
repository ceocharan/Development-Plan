package com.ssolution.driver.service.service;

import com.ssolution.driver.service.entity.Driver;
import com.ssolution.driver.service.repository.DriverRepository;
import com.ssolution.dtos.cab.events.CabEvent;
import com.ssolution.dtos.driver.dtos.DriverDTO;
import com.ssolution.dtos.enums.CommonStatus;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DriverService {
    private DriverRepository driverRepository;
    private KafkaTemplate<String, CabEvent> cabEventKafkaTemplate;
    private static final String ADD_CAB_EVENTS = "add_cab_event";

    public String saveDriver(DriverDTO driverDTO) {
        Driver driver = driverRepository.save(Driver.builder()
                                                    .driverName(driverDTO.getDriverName())
                                                    .driverEmail(driverDTO.getDriverEmail())
                                                    .driverLocation(driverDTO.getDriverLocation())
                                                    .driverStatus(CommonStatus.PENDING)
                                                    .build());
        CabEvent cabEvent = CabEvent.builder()
                .driverId(driver.getDriverId())
                .cabType(driverDTO.getCabDTO().getCabType())
                .registrationNumber(driverDTO.getCabDTO().getRegistrationNumber())
                .build();
        cabEventKafkaTemplate.send(ADD_CAB_EVENTS, cabEvent);

        return "Request is been processed";
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(UUID id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Transactional
    public int updateDriverStatus (UUID driverId, CommonStatus driverStatus) {
         return driverRepository.updateDriverStatus(driverId, driverStatus);
    }
}
