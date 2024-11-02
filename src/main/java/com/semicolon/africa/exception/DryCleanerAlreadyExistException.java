package com.semicolon.africa.exception;

public class DryCleanerAlreadyExistException extends RuntimeException{
    public DryCleanerAlreadyExistException(String message) {
        super(message);
    }
}
