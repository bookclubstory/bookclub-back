package com.emmett.bookclub.domain.model.exception;

import com.emmett.bookclub.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
