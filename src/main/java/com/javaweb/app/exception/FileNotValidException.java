package com.javaweb.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileNotValidException extends RuntimeException {
    public FileNotValidException(String message) {
        super(message);
    }
}
