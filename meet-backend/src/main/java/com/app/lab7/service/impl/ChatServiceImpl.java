package com.app.lab7.service.impl;

import com.app.lab7.dto.chat.ChatDto;
import com.app.lab7.dto.chat.CreateChatDto;
import com.app.lab7.dto.message.CreateMessageDto;
import com.app.lab7.dto.message.MessageDto;
import com.app.lab7.mapper.ChatMapper;
import com.app.lab7.mapper.MessageMapper;
import com.app.lab7.model.Chat;
import com.app.lab7.model.Message;
import com.app.lab7.model.User;
import com.app.lab7.repository.ChatRepository;
import com.app.lab7.repository.MessageRepository;
import com.app.lab7.repository.UserRepository;
import com.app.lab7.service.ChatService;
import com.app.lab7.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final MessageRepository messageRepository;
    private final ChatMapper chatMapper;
    private final MessageMapper messageMapper;

    @Override
    public Chat create(CreateChatDto chatDto) {
        Set<User> usersForChat = new HashSet<>();
        chatDto.getMembers().forEach(user -> {
            User userToAdd = userRepository.findByEmail(user).orElseThrow(
                    () -> new EntityNotFoundException("User with username:" + user + " not found"));
            usersForChat.add(userToAdd);
        });
        Chat chatToSave = chatMapper.toModel(chatDto);
        chatToSave.setUsers(usersForChat);
        return chatRepository.save(chatToSave);
    }
 
    @Override
    public List<ChatDto> getAll() {
        String myEmail = userService.getAuthenticatedUser().getEmail();
        return chatRepository.findAllByEmail(myEmail).stream().map(chatMapper::toDto).toList();
    }

    @Override
    public Chat getById(Long id) {
        return chatRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Chat with id:" + id + " not found"));
    }

    @Override
    public List<MessageDto> getMessages(Long chatId) {
        return messageRepository.findAllByChatId(chatId)
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(messageMapper::toDto)
                .toList();
    }

    @Override
    public MessageDto sendMessage(Long id, CreateMessageDto messageDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        chatRepository.deleteById(id);

    }
}
