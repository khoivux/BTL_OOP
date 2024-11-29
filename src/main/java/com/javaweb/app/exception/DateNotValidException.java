package com.javaweb.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateNotValidException extends RuntimeException{
    public DateNotValidException(String message) {
        super(message);
    }
}
