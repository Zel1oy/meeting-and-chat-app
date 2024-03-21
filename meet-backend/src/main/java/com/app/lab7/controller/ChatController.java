package com.app.lab7.controller;

import com.app.lab7.dto.chat.ChatDto;
import com.app.lab7.dto.chat.CreateChatDto;
import com.app.lab7.dto.message.CreateMessageDto;
import com.app.lab7.dto.message.MessageDto;
import com.app.lab7.model.Chat;
import com.app.lab7.service.ChatService;
import com.app.lab7.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;
    private final MessageService messageService;

    @MessageMapping("/chat/{chatId}/sendMessage")
    public void sendMessage(@DestinationVariable Long chatId, @Valid CreateMessageDto message) {
        messageService.saveMessageToChat(message);
        messagingTemplate.convertAndSend("/topic/chat/" + chatId, message);
    }

    @GetMapping
    public List<ChatDto> getAll() {
        return chatService.getAll();
    }

    @GetMapping("/{id}/getMessages")
    public List<MessageDto> getMessages(@PathVariable Long id) {
        return chatService.getMessages(id);
    }

    @PostMapping("/create")
    public Chat create(@RequestBody @Valid CreateChatDto createChatDto) {
        return chatService.create(createChatDto);
    }
}
