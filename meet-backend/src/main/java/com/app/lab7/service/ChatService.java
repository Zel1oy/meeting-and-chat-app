package com.app.lab7.service;

import com.app.lab7.dto.chat.ChatDto;
import com.app.lab7.dto.chat.CreateChatDto;
import com.app.lab7.dto.message.CreateMessageDto;
import com.app.lab7.dto.message.MessageDto;
import com.app.lab7.model.Chat;

import java.util.List;

public interface ChatService {
    Chat create(CreateChatDto chatDto);

    List<ChatDto> getAll();

    Chat getById(Long id);

    List<MessageDto> getMessages(Long id);

    MessageDto sendMessage(Long id, CreateMessageDto messageDto);

    void deleteById(Long id);
}
