package com.app.lab7.dto.message;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateMessageDto {
    @NotEmpty
    private String content;
    private LocalDateTime time;
    private Long chat;
    private String sender;
}
