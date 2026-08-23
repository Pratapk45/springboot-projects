package com.example.demo.exception;

public class ExcessiveLeaveException extends RuntimeException {

    public ExcessiveLeaveException(String message) {
        super(message);
    }
}
