package com.sparta.week03project.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {CustomException.class})
    public ResponseEntity handleCustomException(CustomException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setErrorCode(ex.getErrorCode().getErrorCode());
        errorResult.setHttpStatus(ex.getErrorCode().getHttpStatus());
        errorResult.setErrorMessage(ex.getErrorCode().getErrorMessage());

        return new ResponseEntity(errorResult, errorResult.getHttpStatus());
    }
    // 실행 안됨
//    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity handleAccessDeniedException(AccessDeniedException ex) {
//        ErrorResult errorResult = new ErrorResult();
//        errorResult.setErrorCode(ErrorCode.NOT_ALLOWED.getErrorCode());
//        errorResult.setHttpStatus(ErrorCode.NOT_ALLOWED.getHttpStatus());
//        errorResult.setErrorMessage(ErrorCode.NOT_ALLOWED.getErrorMessage());
//
//        return new ResponseEntity(errorResult, errorResult.getHttpStatus());
//    }

}
