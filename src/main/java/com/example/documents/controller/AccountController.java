package com.example.documents.controller;

import com.example.documents.model.Account;
import com.example.documents.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ResponseBody
    @RequestMapping("/accounts")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }
}
