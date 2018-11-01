package com.example.documents.controller;

import com.example.documents.exceptionhandler.BindingException;
import com.example.documents.model.Account;
import com.example.documents.model.FormDataWithFile;
import com.example.documents.service.AccountService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.modelmbean.ModelMBean;
import javax.validation.Valid;
import java.io.*;
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

    @RequestMapping(value = "/accountPost", method = RequestMethod.POST)
    public void postAccounts(FormDataWithFile formDataWithFile, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new BindingException(bindingResult.getAllErrors().toString());
        }
        System.out.println("Testi: " + formDataWithFile);

        accountService.copyFileToWorkspace(formDataWithFile.getImageFile());

        accountService.saveAccount(toAccount(formDataWithFile.getName(), formDataWithFile.getAlter(), formDataWithFile.getEmail()));
    }

    private Account toAccount(String name, String alter, String email) {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setEmailAdresse(email);
        account.setNachname(name);
        account.setVorname(name);
        account.setTelefonNummer(alter);
        return account;
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

    @ExceptionHandler(BindingException.class)
    public void handleBindingExceptions() {
        System.out.println("Binding exception ");
    }

    @ExceptionHandler(Exception.class)
    public void handleException() {
        System.out.println("Es gab einen Fehler: ");

    }
}
