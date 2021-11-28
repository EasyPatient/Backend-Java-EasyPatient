package com.easypatient.easypatient.users.appuser;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime bornDate;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private String email;
    private String phoneNumber;
    private String phoneAreaCode;
    private UUID staffId;
    private boolean locked;
    private boolean enabled;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public AppUser(String username,
                   String password,
                   String firstName,
                   String lastName,
                   LocalDateTime bornDate,
                   AppUserRole appUserRole,
                   String email,
                   String phoneNumber,
                   String phoneAreaCode,
                   UUID staffId) {
        LocalDateTime date = LocalDateTime.now();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.appUserRole = appUserRole;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.phoneAreaCode = phoneAreaCode;
        this.staffId = staffId;
        this.updatedAt = date;
        this.createdAt = date;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
