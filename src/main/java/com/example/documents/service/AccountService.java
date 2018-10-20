package com.example.documents.service;

import com.example.documents.model.Account;
import com.example.documents.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account toSave){
        return accountRepository.save(toSave);
    }

    public List<Account> getAllAccounts(){
        List<Account> accounts = (List<Account>)accountRepository.findAll();
        return accounts;
    }
}
