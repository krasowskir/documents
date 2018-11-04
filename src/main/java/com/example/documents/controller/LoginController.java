package com.example.documents.controller;

import com.example.documents.model.CredentialsDto;
import com.example.documents.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "/login")
    public void login(CredentialsDto credentials){


    }
}
