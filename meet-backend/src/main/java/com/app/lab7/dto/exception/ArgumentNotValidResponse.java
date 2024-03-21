package com.app.lab7.dto.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ArgumentNotValidResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String[] errors;
}
