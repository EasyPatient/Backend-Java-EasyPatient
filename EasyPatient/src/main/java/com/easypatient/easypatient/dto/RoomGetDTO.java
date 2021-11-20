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
public class RoomGetDTO {
    private final UUID id;
    private int number;
    private String name;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
