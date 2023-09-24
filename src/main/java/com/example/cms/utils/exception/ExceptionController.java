package com.example.cms.utils.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(CommonException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CommonException e){
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }
}
