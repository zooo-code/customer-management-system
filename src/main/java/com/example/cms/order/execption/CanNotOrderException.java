package com.example.cms.order.execption;

public class CanNotOrderException extends RuntimeException {


    public CanNotOrderException() {
    }

    public CanNotOrderException(String message) {
        super(message);
    }

    public CanNotOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotOrderException(Throwable cause) {
        super(cause);
    }
}
