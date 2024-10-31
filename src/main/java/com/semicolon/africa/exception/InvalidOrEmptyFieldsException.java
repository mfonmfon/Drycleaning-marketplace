package com.semicolon.africa.exception;

public class InvalidOrEmptyFieldsException extends RuntimeException {
    public InvalidOrEmptyFieldsException(String message) {
        super(message);
    }
}
