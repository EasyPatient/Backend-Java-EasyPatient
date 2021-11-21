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
public class Medicaments {
    private final UUID id;
    private String name;
    private String type;
    private String value;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
