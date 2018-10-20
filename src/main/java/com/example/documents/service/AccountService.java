package com.example.documents.service;

import com.example.documents.model.Account;
import com.example.documents.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account toSave){
        return accountRepository.save(toSave);
    }
}
