package com.app.lab7.mapper;

import com.app.lab7.config.MapperConfig;
import com.app.lab7.dto.message.CreateMessageDto;
import com.app.lab7.dto.message.MessageDto;
import com.app.lab7.model.Chat;
import com.app.lab7.model.Message;
import com.app.lab7.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface MessageMapper {
    default Chat mapChatId(Long chatId) {
        return new Chat(chatId);
    }

    default User mapUserEmail(String email) {
        return new User(email);
    }

    Message toModel(CreateMessageDto createMessageDto);

    @Mapping(target = "sender", source = "sender.email")
    @Mapping(target = "chatId", source = "chat.id")
    MessageDto toDto(Message message);
}
