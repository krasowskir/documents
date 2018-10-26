package com.example.documents.controller;

import com.example.documents.model.Account;
import com.example.documents.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class AccountController {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    private final static Resource PICTURES_DIR = new FileSystemResource("./images");

    @ResponseBody
    @RequestMapping("/accounts")
    public List<Account> getAllAccounts(){
        LOGGER.info("getAccounts on accountController in " + this.toString() +" to service " + accountService.toString());
        return accountService.getAllAccounts();
    }

    @RequestMapping(value = "/fotoupload", method = RequestMethod.POST)
    public void uploadFoto(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        File tmpFile = File.createTempFile("pic",getFileExtension(filename),PICTURES_DIR.getFile());
    }

    @RequestMapping(value = "/accountPost", method = RequestMethod.POST)
    public void postAccounts(@RequestBody AccountDTO account){
        System.out.println("Testi: " + account);
        accountService.saveAccount(account.toEntity());
    }

    private static String getFileExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }
}
