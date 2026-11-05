package com.ssolution.driver.service.controller;

import com.ssolution.driver.service.entity.Driver;
import com.ssolution.driver.service.service.DriverService;
import com.ssolution.dtos.driver.dtos.DriverDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
@AllArgsConstructor
public class DriverController {
    private DriverService driverService;

    @PostMapping
    public ResponseEntity<String> saveDriver(@RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.saveDriver(driverDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable UUID id) {
        return new ResponseEntity<>(driverService.getDriverById(id), HttpStatus.OK);
    }
}
