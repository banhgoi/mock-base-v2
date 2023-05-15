package com.example.mockbasev2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomAPIException extends RuntimeException{
    public CustomAPIException(String code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
    public CustomAPIException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;
    private String details;


}
