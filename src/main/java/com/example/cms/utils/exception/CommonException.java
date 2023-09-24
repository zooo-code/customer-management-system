package com.example.cms.utils.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{
    ErrorCode errorCode;

    public CommonException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
