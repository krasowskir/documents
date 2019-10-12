package com.example.documents.service;

import com.example.documents.model.Account;
import com.example.documents.repository.AccountRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("meinAccountService")
public class AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account saveAccount(Account toSave) {
        return accountRepository.save(toSave);
    }

    public Optional<Account> fetchAccount(UUID id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAllAccounts() {
        LOGGER.info("getting all Accounts accountService in " + this.toString() + " to repository " + accountRepository.toString());
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        return accounts;
    }

    public void copyFileToWorkspace(MultipartFile inputFile) throws IOException {
        Resource PICTURES_DIR = new ClassPathResource("images");
        String filename = inputFile.getOriginalFilename();
        File tmpFile = File.createTempFile("pic", getFileExtension(filename), PICTURES_DIR.getFile());
        System.out.println("uploaded foto: " + tmpFile.toString());

        InputStream inStr = inputFile.getInputStream();
        OutputStream fout = new FileOutputStream(tmpFile);
        IOUtils.copy(inStr, fout);
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }
}
