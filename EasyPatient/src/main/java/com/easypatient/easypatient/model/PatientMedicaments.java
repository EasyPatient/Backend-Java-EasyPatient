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
public class PatientMedicaments {
    private final UUID patientId;
    private final UUID medicamentsId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

}
