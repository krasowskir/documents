package com.example.documents.controller;

import com.example.documents.exceptionhandler.BindingException;
import com.example.documents.model.Account;
import com.example.documents.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
@RestController
public class AccountController {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;


    @ResponseBody
    @RequestMapping("/accounts")
    public List<Account> getAllAccounts() {
        LOGGER.info("getAccounts on accountController in " + this.toString() + " to service " + accountService.toString());
        return accountService.getAllAccounts();
    }

    @ResponseBody
    @GetMapping("/account/id/{accountUuid}")
    public Account getAccount(@PathVariable("accountUuid") String accountUuid ){
        return accountService.getAccountById(UUID.fromString(accountUuid)).get();
    }

    @ResponseBody
    @RequestMapping(value = "/account/image/id/{accountId}", method = RequestMethod.GET)
    public byte[] getImageForAccountId(@PathVariable("accountId") UUID accountId){
        byte[] img =  accountService.getImageFromAccount(accountId);
        if (img == null){
            System.out.println("img is null");
        }
        return img;
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

    @ExceptionHandler(BindingException.class)
    public void handleBindingExceptions() {
        System.out.println("Binding exception ");
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        LOGGER.error("Es gab einen Fehler: ", e);

    }
}
