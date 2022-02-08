package com.emmett.bookclub.domain.model.exception;

import com.emmett.bookclub.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BusinessException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
