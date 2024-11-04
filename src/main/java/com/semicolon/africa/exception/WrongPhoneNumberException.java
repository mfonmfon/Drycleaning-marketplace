package com.semicolon.africa.exception;

public class WrongPhoneNumberException extends RuntimeException {
    public WrongPhoneNumberException(String message) {
        super(message);
    }
}
