package com.sparta.week03project.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResult {
    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;
}
