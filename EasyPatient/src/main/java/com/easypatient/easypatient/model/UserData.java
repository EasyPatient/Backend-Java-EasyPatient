package com.easypatient.easypatient.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserData {
    private final UUID id;
    private final String username;
    private String password;
    private UserData.Role role;
    private String email;
    private String phoneNumber;
    private String phoneAreaCode;
    private final UUID staffId;
    private LocalDateTime updatedAt;
    private final LocalDateTime createdAt;

    private enum Role {
        DOCTOR, NURSE;
    }
}
