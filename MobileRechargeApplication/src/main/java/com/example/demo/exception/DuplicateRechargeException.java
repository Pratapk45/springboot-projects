package com.example.demo.exception;


public class DuplicateRechargeException extends RuntimeException {

    public DuplicateRechargeException(String message) {
        super(message);
    }
}