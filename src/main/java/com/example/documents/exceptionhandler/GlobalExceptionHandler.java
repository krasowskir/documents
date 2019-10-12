package com.example.documents.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public void handleGlobalExceptions(){
        System.out.println("Es gab einen globalen Fehler");
    }
}
