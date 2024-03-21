package com.app.lab7.controller;

import com.app.lab7.dto.user.UserLoginRequestDto;
import com.app.lab7.dto.user.UserLoginResponseDto;
import com.app.lab7.dto.user.UserRegistrationRequestDto;
import com.app.lab7.dto.user.UserResponseDto;
import com.app.lab7.mapper.UserMapper;
import com.app.lab7.service.AuthenticationService;
import com.app.lab7.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request) {
        return userService.register(request);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public UserResponseDto getUser() {
        return userMapper.toResponseDto(userService.getAuthenticatedUser());
    }
}
