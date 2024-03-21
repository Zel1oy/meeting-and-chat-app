package com.app.lab7.service;

import com.app.lab7.dto.user.UserRegistrationRequestDto;
import com.app.lab7.dto.user.UserResponseDto;
import com.app.lab7.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request);

    User getAuthenticatedUser();

    User getUserByEmail(String email);
}
