package com.app.lab7.exception;

import com.app.lab7.dto.exception.ArgumentNotValidResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String[] errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(this::getErrorMessage)
                .toArray(String[]::new);
        return createResponseEntityFromExceptionErrors(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistrationException.class)
    protected ResponseEntity<Object> handleRegistrationException(
            RegistrationException e
    ) {
        return createResponseEntityFromExceptionErrors(
                new String[]{e.getMessage()}, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> createResponseEntityFromExceptionErrors(
            String[] errors, HttpStatus status) {
        ArgumentNotValidResponse notValidResponse = new ArgumentNotValidResponse();
        notValidResponse.setTimestamp(LocalDateTime.now());
        notValidResponse.setStatus(status);
        notValidResponse.setErrors(errors);
        return new ResponseEntity<>(notValidResponse, HttpStatusCode.valueOf(status.value()));
    }

    private String getErrorMessage(ObjectError e) {
        if (e instanceof FieldError) {
            String field = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            return field + " " + message;
        }
        return e.getDefaultMessage();
    }
}
