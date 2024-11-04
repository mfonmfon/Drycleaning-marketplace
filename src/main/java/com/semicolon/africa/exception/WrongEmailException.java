package com.semicolon.africa.exception;

public class WrongEmailException extends RuntimeException {
    public WrongEmailException(String message) {
        super(message);
    }
}
