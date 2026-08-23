package com.example.demo.exception;


public class InvalidRechargePlanException extends RuntimeException {

    public InvalidRechargePlanException(String message) {
        super(message);
    }
}