package com.ssolution.cab.service.entity;

import com.ssolution.dtos.enums.CabTypes;
import com.ssolution.dtos.enums.CommonStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cableId;
    private UUID driverId;
    private String registrationNumber;
    @Enumerated(EnumType.STRING)
    private CabTypes cabType;
    @Enumerated(EnumType.STRING)
    private CommonStatus status;
}
