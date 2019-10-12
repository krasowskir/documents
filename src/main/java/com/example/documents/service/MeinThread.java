package com.example.documents.service;

import com.example.documents.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeinThread implements Runnable {

    Logger LOGGER = LoggerFactory.getLogger(MeinThread.class);

    private AccountService accountService;

    public MeinThread(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("=================");
            for (Account elem : accountService.getAllAccounts()){
                System.out.println("account: " + elem);
            }
        } catch (Exception e) {
            LOGGER.error("irgendwas mit thread...");
        }

    }
}
