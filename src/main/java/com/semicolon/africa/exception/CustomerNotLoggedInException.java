package com.semicolon.africa.exception;

public class CustomerNotLoggedInException extends RuntimeException {
    public CustomerNotLoggedInException(String message) {
        super(message);
    }
}
