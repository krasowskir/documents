package com.example.documents.exceptions;

public class CreditCardNotFoundException extends RuntimeException{

    public CreditCardNotFoundException() {
    }

    public CreditCardNotFoundException(String message) {
        super(message);
    }

    public CreditCardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
