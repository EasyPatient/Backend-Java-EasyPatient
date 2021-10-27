package com.easypatient.easypatient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Person {
    private final UUID id;
    @NotBlank
    private final String name;
    private final Date arrivedAt;
    private final LocalDateTime createdAt;
    private int age;
    private UUID bedId;
    private LocalDateTime updatedAt;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("age") int age,
                  @JsonProperty("bedId") UUID bedId,
                  @JsonProperty("arrivedAt") Date arrivedAt) {
        LocalDateTime presentDate = LocalDateTime.now();

        this.id = id;
        this.name = name;
        this.age = age;
        this.bedId = bedId;
        this.arrivedAt = arrivedAt;
        this.createdAt = presentDate;
        this.updatedAt = createdAt;
    }

}
