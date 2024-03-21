package com.app.lab7.service.impl;

import com.app.lab7.dto.message.CreateMessageDto;
import com.app.lab7.mapper.MessageMapper;
import com.app.lab7.model.Message;
import com.app.lab7.repository.MessageRepository;
import com.app.lab7.service.ChatService;
import com.app.lab7.service.MessageService;
import com.app.lab7.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserService userService;
    private final ChatService chatService;

    @Override
    public Message saveMessageToChat(CreateMessageDto message) {
        Message messageToSave = messageMapper.toModel(message);
        messageToSave.setSender(userService.getUserByEmail(message.getSender()));
        messageToSave.setChat(chatService.getById(message.getChat()));
        return messageRepository.save(messageToSave);
    }
}
