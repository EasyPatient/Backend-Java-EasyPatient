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
public class PatientMedicamentsGetDTO {
    private final UUID patientId;
    private final UUID medicamentsId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
