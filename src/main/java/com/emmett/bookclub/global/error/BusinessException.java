package com.emmett.bookclub.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException{
    private HttpStatus status;

    public BusinessException(HttpStatus status) {
        this.status = status;
    }

    public BusinessException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
