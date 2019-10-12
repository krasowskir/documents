package com.example.documents.exceptionhandler;

public class BindingException extends RuntimeException {

    public BindingException( String message){
        super(message);
    }
    public BindingException(Throwable cause, String message){
        super(message, cause);
    }
}
