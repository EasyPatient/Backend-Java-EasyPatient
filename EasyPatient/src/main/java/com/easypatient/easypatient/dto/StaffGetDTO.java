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
public class StaffGetDTO {
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
