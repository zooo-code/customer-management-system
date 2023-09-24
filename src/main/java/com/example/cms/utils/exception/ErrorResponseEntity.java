package com.example.cms.utils.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class ErrorResponseEntity {
    private int status;
    private String code;
    private String message;

    @Builder
    public ErrorResponseEntity(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ErrorResponseEntity.builder()
                        .status(e.getHttpStatus().value())
                        .code(e.name())
                        .message(e.getMessage())
                        .build()
                );
    }

}
