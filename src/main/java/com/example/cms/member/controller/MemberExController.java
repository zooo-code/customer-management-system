package com.example.cms.member.controller;

import com.example.cms.member.exception.MemberAlreadyBlindException;
import com.example.cms.member.exception.MemberAlreadyExistException;
import com.example.cms.member.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExController {

    @ExceptionHandler(MemberAlreadyExistException.class)
    public ResponseEntity<?> handleMemberAlreadyExistEx(MemberAlreadyExistException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> handleMemberNotFoundEx(MemberNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(MemberAlreadyBlindException.class)
    public ResponseEntity<?> handleMemberAlreadyBlindEx(MemberNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
