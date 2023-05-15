package com.example.mockbasev2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private String code;
    private String message;
    private String details;
}
