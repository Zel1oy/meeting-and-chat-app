package com.app.lab7.service;

import com.app.lab7.dto.message.CreateMessageDto;
import com.app.lab7.model.Message;

public interface MessageService {
    Message saveMessageToChat(CreateMessageDto message);
}
