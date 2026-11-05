package com.ssolution.dtos.driver.dtos;

import com.ssolution.dtos.cab.dtos.CabDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverDTO {
    private String driverName;
    private String driverEmail;
    private String driverLocation;
    private CabDTO cabDTO;
}
