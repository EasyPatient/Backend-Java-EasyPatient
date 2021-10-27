package com.easypatient.easypatient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Bed {
    private final UUID id;
    private final LocalDateTime createdAt;
    private int number;
    private UUID patientId;
    private UUID roomId;
    private LocalDateTime updatedAt;

    public Bed(@JsonProperty("id") UUID id,
               @JsonProperty("number") int number,
               @JsonProperty("patientId") UUID patientId,
               @JsonProperty("roomId") UUID roomId) {
        LocalDateTime presentTime = LocalDateTime.now();

        this.id = id;
        this.number = number;
        this.patientId = patientId;
        this.roomId = roomId;
        this.createdAt = presentTime;
        this.updatedAt = createdAt;
    }


}
