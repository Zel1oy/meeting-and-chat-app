package com.app.lab7.mapper;

import com.app.lab7.config.MapperConfig;
import com.app.lab7.dto.user.UserLoginResponseDto;
import com.app.lab7.dto.user.UserRegistrationRequestDto;
import com.app.lab7.dto.user.UserResponseDto;
import com.app.lab7.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserLoginResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);

    UserResponseDto toResponseDto(User user);
}
