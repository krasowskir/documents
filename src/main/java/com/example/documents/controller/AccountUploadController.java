package com.example.documents.controller;

import com.example.documents.model.Account;
import com.example.documents.model.AccountDto;
import com.example.documents.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class AccountUploadController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/accountPost", method = RequestMethod.POST)
    public void postAccounts(@RequestParam(value = "account") String account, @RequestParam("myImage") MultipartFile multipartFile) throws IOException {

        if (multipartFile.getSize() == 0 ){
            System.out.println("Size ist 0");
        } if (multipartFile.getSize() != 0 && account.length() != 0) {
            AccountDto tmpAccount = objectMapper.readValue(account, AccountDto.class);
            if (tmpAccount != null){
                System.out.println("====Eroflg====");
                Account toPersist = new Account();
                toPersist.setName(tmpAccount.getName());
                toPersist.setAlter(Integer.parseInt(tmpAccount.getAlter()));
                toPersist.setEmailAdresse(tmpAccount.getEmail());
                toPersist.setTelefonNummer(tmpAccount.getTelefonNummer());
                toPersist.setId(UUID.randomUUID());
                toPersist.setImage(multipartFile.getBytes());

                accountService.saveAccount(toPersist);
            }
        }
    }
}
