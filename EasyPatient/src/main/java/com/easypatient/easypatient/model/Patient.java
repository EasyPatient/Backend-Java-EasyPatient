package com.easypatient.easypatient.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Patient {
    private final UUID id;
    private final String name;
    private int age;
    private UUID bedId;
    private final LocalDateTime arrivedAt;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
