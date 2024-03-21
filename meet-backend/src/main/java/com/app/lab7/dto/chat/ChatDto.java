package com.app.lab7.dto.chat;

import com.app.lab7.dto.message.MessageDto;
import com.app.lab7.dto.user.UserResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class ChatDto {
    private Long id;
    private String name;
    private List<UserResponseDto> members;
    private List<MessageDto> messages;
}
