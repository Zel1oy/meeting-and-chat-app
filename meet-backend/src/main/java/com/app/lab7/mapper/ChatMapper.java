package com.app.lab7.mapper;

import com.app.lab7.config.MapperConfig;
import com.app.lab7.dto.chat.ChatDto;
import com.app.lab7.dto.chat.CreateChatDto;
import com.app.lab7.model.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {UserMapper.class, MessageMapper.class})
public interface ChatMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "users", ignore = true)
    Chat toModel(CreateChatDto createChatDto);

    @Mapping(target = "members", source = "users")
    ChatDto toDto(Chat chat);
}
