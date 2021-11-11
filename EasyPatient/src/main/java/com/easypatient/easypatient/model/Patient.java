package com.easypatient.easypatient.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Patient {
    private final UUID id;
    @NotBlank
    private final String name;
    private int age;
    private UUID bedId;
    private final LocalDateTime arrivedAt;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
