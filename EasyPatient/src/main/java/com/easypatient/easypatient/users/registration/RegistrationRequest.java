package com.easypatient.easypatient.users.registration;

import com.easypatient.easypatient.users.appuser.AppUserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final LocalDateTime bornDate;
    private final AppUserRole appUserRole;
    private final String email;
    private final String phoneNumber;
    private final String phoneAreaCode;
    private final UUID staffId;
}
