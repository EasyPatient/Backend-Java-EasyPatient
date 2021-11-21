package com.easypatient.easypatient.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class SmartbandDataDTO {
    private final UUID smartbandId;
    private UUID patientId;
    private String heartRate;
    private String oxygen;
    private String temperature;
    private String battery;
}
