package com.easypatient.easypatient.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class StaffDTO {
    private String name;
    private String email;
    private String phone;
    private String phoneAreaCode;
    private String password;
    private String role;
}
