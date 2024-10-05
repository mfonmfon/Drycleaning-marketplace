package com.semicolon.africa.exception;

public class EmptyFieldsInputException extends  RuntimeException{
    public EmptyFieldsInputException(String message){
        super(message);
    }
}
