package com.semicolon.africa.exception;

public class CustomerEmailAlreadyExistsException extends RuntimeException {
    public CustomerEmailAlreadyExistsException(String message) {
        super(message);
    }
}
