package com.ssolution.dtos.cab.dtos;

import com.ssolution.dtos.enums.CabTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CabDTO {
    private String registrationNumber;
    private CabTypes cabType;
}
