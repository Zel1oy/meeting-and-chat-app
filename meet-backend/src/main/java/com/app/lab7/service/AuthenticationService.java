package com.app.lab7.service;

import com.app.lab7.dto.user.UserLoginRequestDto;
import com.app.lab7.dto.user.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto request);
}
