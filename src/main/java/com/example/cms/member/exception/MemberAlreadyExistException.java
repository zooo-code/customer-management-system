package com.example.cms.member.exception;

public class MemberAlreadyExistException extends RuntimeException {

    public MemberAlreadyExistException() {
    }

    public MemberAlreadyExistException(String message) {
        super(message);
    }


}
