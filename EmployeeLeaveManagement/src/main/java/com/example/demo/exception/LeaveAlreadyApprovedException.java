package com.example.demo.exception;


public class LeaveAlreadyApprovedException extends RuntimeException {

    public LeaveAlreadyApprovedException(String message) {
        super(message);
    }
}