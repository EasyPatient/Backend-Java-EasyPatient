package com.easypatient.easypatient.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class SmartbandData {
    private final UUID smartbandId;
    private UUID patientId;
    private String heartRate;
    private String oxygen;
    private String temperature;
    private String battery;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
