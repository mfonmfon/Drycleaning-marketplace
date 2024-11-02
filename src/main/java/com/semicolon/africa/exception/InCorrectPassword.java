package com.semicolon.africa.exception;

public class InCorrectPassword extends RuntimeException {
    public InCorrectPassword(String message) {
        super(message);
    }
}
