package com.example.documents.controller;

import com.example.documents.exceptionhandler.BindingException;
import com.example.documents.model.Account;
import com.example.documents.model.AccountDto;
import com.example.documents.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
@RestController
public class AccountController {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private ObjectMapper objectMapper;

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

    @PostMapping("/account/id/{accountUuid}")
    public void postAccounts(@PathVariable("accountUuid") String accountUuid,
                             @RequestParam("account") String account,
                             @RequestParam("myImage") MultipartFile multipartFile) throws IOException {

        if (multipartFile.getSize() == 0 ){
            LOGGER.error("Size ist 0");
        } if (multipartFile.getSize() != 0 && account.length() != 0) {
            Account toPersist = accountService.getAccountById(UUID.fromString(accountUuid)).get();
            AccountDto tmpAccount = objectMapper.readValue(account, AccountDto.class);
            if (tmpAccount != null && toPersist != null){
                System.out.println("====Eroflg====");
                toPersist.setName(tmpAccount.getName());
                toPersist.setAlter(Integer.parseInt(tmpAccount.getAlter()));
                toPersist.setEmailAdresse(tmpAccount.getEmail());
                toPersist.setTelefonNummer(tmpAccount.getTelefonNummer());
                toPersist.setImage(multipartFile.getBytes());

                accountService.saveAccount(toPersist);
            } else {
                LOGGER.error("account oder accountDto waren null: {}, {}", toPersist, tmpAccount);
            }
        }
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
