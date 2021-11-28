package com.easypatient.easypatient.users.registration;

import com.easypatient.easypatient.users.appuser.AppUser;
import com.easypatient.easypatient.users.appuser.AppUserRole;
import com.easypatient.easypatient.users.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public void register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getRole()

                )
        );
    }
}
