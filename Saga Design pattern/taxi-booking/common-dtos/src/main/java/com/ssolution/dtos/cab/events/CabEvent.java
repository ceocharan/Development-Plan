package com.ssolution.dtos.cab.events;

import com.ssolution.dtos.enums.CabTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CabEvent {
    private UUID driverId;
    private String registrationNumber;
    private CabTypes cabType;
}
