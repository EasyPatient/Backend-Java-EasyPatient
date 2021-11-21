package com.easypatient.easypatient.dto;

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
public class PatientDTO {
    private final String name;
    private int age;
    private UUID bedId;
    private final LocalDateTime arrivedAt;
}
