package com.example.cms.member.exception;

public class MemberAlreadyExistException extends RuntimeException {

    public MemberAlreadyExistException() {
    }

    public MemberAlreadyExistException(String message) {
        super(message);
    }

    public MemberAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
