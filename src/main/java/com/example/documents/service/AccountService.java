package com.example.documents.service;

import com.example.documents.model.Account;
import com.example.documents.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account saveAccount(Account toSave){
        return accountRepository.save(toSave);
    }

    public Optional<Account> fetchAccount(UUID id){
        return accountRepository.findById(id);
    }

    public List<Account> getAllAccounts(){
        List<Account> accounts = (List<Account>)accountRepository.findAll();
        return accounts;
    }
}
