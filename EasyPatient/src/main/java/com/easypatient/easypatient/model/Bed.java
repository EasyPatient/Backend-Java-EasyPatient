package com.easypatient.easypatient.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Bed {
    private final UUID id;
    private int number;
    private UUID roomId;
    private LocalDateTime updatedAt;
    private final LocalDateTime createdAt;

}
