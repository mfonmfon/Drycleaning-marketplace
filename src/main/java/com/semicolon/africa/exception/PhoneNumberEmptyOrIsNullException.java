package com.semicolon.africa.exception;

public class PhoneNumberEmptyOrIsNullException extends RuntimeException {
    public PhoneNumberEmptyOrIsNullException(String message) {
        super(message);
    }
}
