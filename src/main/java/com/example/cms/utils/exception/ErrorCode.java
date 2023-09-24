package com.example.cms.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재 합니다."),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "데이터가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
