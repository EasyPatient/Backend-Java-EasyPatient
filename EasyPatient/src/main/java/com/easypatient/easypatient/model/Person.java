package com.easypatient.easypatient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Person {
    private final UUID id;

    @NotBlank
    private final String name;

    private int age;
    private UUID bedId;
    private final Date arrivedAt;
    private final LocalDateTime createdAt;
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

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public UUID getBedId() {
        return this.bedId;
    }

    public Date getArrivedAt() {
        return this.arrivedAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBedId(UUID bedId) {
        this.bedId = bedId;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
