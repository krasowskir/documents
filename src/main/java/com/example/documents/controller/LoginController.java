package com.example.documents.controller;

import com.example.documents.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    AccountRepository accountRepository;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request){
        System.out.println("test: " + " " + request);

    }
}
