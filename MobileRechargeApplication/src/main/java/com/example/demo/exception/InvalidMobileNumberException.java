package com.example.demo.exception;


public class InvalidMobileNumberException extends RuntimeException {

    public InvalidMobileNumberException(String message) {
        super(message);
    }
}