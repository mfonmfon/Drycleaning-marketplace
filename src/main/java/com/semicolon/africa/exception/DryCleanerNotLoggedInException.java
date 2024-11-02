package com.semicolon.africa.exception;

public class DryCleanerNotLoggedInException extends RuntimeException {
    public DryCleanerNotLoggedInException(String message) {
        super(message);
    }
}
