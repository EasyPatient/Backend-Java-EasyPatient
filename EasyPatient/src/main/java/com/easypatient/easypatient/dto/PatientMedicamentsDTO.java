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
public class PatientMedicamentsDTO {
    private final UUID patientId;
    private final UUID medicamentsId;
}
