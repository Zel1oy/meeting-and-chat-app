package com.app.lab7.dto.message;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private String content;
    private LocalDateTime time;
    private String sender;
    private Long chatId;
}
