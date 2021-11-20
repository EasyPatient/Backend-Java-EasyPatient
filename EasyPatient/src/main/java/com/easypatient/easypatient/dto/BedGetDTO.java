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
public class BedGetDTO {
    private final UUID id;
    private int number;
    private UUID patientId;
    private UUID roomId;
    private LocalDateTime updatedAt;
    private final LocalDateTime createdAt;
}
