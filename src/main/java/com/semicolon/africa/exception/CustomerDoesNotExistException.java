package com.semicolon.africa.exception;

public class CustomerDoesNotExistException extends RuntimeException {
    public CustomerDoesNotExistException(String message){
        super(message);
    }
}
