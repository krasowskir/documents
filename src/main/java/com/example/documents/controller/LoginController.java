package com.example.documents.controller;

import com.example.documents.model.Account;
import com.example.documents.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    AccountRepository accountRepository;

    private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request){
        System.out.println("test: " + " " + request);

    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void getLogin(HttpServletRequest request) throws IOException {

    }
}
