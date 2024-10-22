package com.semicolon.africa.exception;

public class EmptyOrderException extends RuntimeException {
    public EmptyOrderException(String message) {
        super(message);
    }
}
