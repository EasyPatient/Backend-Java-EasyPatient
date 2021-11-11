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
public class Staff {
    private final UUID id;
    private String name;
    private String email;
    private String phone;
    private String phoneAreaCode;
    private String password;
    private String role;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
