package com.semicolon.africa.exception;

public class PasswordEmptyOrIsNullException extends RuntimeException {
    public PasswordEmptyOrIsNullException(String message) {
        super(message);
    }
}
