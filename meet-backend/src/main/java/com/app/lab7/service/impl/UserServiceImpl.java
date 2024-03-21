package com.app.lab7.service.impl;

import com.app.lab7.dto.user.UserRegistrationRequestDto;
import com.app.lab7.dto.user.UserResponseDto;
import com.app.lab7.enums.RoleName;
import com.app.lab7.exception.RegistrationException;
import com.app.lab7.mapper.UserMapper;
import com.app.lab7.model.User;
import com.app.lab7.repository.RoleRepository;
import com.app.lab7.repository.UserRepository;
import com.app.lab7.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("User with email "
                    + request.getEmail() + " already exists");
        }
        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(roleRepository.getRoleByName(RoleName.ROLE_USER)));
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RegistrationException("User with email "
                        + email + " not found"));
    }
}
