package com.semicolon.africa.exception;

public class EmailEmptyOrIsNullException extends RuntimeException {
    public EmailEmptyOrIsNullException(String message) {
        super(message);
    }
}
