package com.ssolution.dtos.driver.events;

import com.ssolution.dtos.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateDriverStatusEvent {
    private UUID driverId;
    private CommonStatus driverStatus;
}
