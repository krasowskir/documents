package com.example.documents.service;

import com.example.documents.controller.AccountController;
import com.example.documents.model.Account;
import com.example.documents.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("meinAccountService")
public class AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account toSave){
        return accountRepository.save(toSave);
    }

    public List<Account> getAllAccounts(){
        LOGGER.info("getting all Accounts accountService in " + this.toString() + " to repository " + accountRepository.toString());
        List<Account> accounts = (List<Account>)accountRepository.findAll();
        return accounts;
    }
}
