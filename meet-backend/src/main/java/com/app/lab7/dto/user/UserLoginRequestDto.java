package com.app.lab7.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginRequestDto {
    @NotEmpty
    @Email
    @Length(min = 1, max = 255)
    private String email;
    @NotEmpty
    @Length(min = 8, max = 255)
    private String password;
}
