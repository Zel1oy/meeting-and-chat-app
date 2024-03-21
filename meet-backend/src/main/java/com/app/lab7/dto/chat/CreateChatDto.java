package com.app.lab7.dto.chat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.Data;

@Data
public class CreateChatDto {
    @Size(min = 3, max = 255)
    private String name;
    @NotEmpty
    private Set<String> members;
}
