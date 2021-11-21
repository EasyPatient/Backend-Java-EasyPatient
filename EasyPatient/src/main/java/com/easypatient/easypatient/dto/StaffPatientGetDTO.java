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
public class StaffPatientGetDTO {
    private final UUID patientId;
    private UUID staffId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
