package com.ssolution.driver.service.entity;

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
@Table
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID driverId;
    private String driverName;
    private String driverEmail;
    private String driverLocation;
    @Enumerated(EnumType.STRING)
    private CommonStatus driverStatus;
}
