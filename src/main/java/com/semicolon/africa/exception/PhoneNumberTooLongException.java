package com.semicolon.africa.exception;

public class PhoneNumberTooLongException extends RuntimeException {
    public PhoneNumberTooLongException(String message) {
        super(message);
    }
}
