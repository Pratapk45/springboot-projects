package com.example.demo.exception;


public class RechargeNotFoundException extends RuntimeException {

    public RechargeNotFoundException(String message) {
        super(message);
    }
}
