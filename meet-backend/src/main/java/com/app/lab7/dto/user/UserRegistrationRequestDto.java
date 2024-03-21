package com.app.lab7.dto.user;

import com.app.lab7.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@FieldMatch(field = "password",
        fieldToMatch = "repeatPassword",
        message = "Passwords don't match")
public class UserRegistrationRequestDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Length(min = 8, max = 255)
    private String password;
    @NotNull
    @Length(min = 8, max = 255)
    private String repeatPassword;
    @NotNull
    @Length(min = 1, max = 255)
    private String firstName;
    @NotNull
    @Length(min = 1, max = 255)
    private String lastName;
}
