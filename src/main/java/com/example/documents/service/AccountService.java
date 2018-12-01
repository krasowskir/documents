package com.example.documents.service;

import com.example.documents.model.Account;
import com.example.documents.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public Account saveAccount(Account toSave){

        try {
            ListenableFuture result = kafkaTemplate.send("myTopic", UUID.randomUUID().toString(), objectMapper.writeValueAsString(toSave));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return accountRepository.save(toSave);
    }

    public List<Account> getAllAccounts(){
        LOGGER.info("getting all Accounts accountService in " + this.toString() + " to repository " + accountRepository.toString());
        List<Account> accounts = (List<Account>)accountRepository.findAll();
        return accounts;
    }

    public byte[] getImageFromAccount(UUID toFind) {
        return accountRepository.findById(toFind).get().getImage();

    }

    //ToDo: does not work, since image dir is not created in classpath
    public void copyFileToWorkspace(MultipartFile inputFile) throws IOException {
        Resource PICTURES_DIR = new ClassPathResource("images");
        String filename = inputFile.getOriginalFilename();
        File tmpFile = File.createTempFile("pic",getFileExtension(filename),PICTURES_DIR.getFile());
        System.out.println("uploaded foto: " + tmpFile.toString());

        InputStream inStr = inputFile.getInputStream();
        OutputStream fout = new FileOutputStream(tmpFile);
        IOUtils.copy(inStr, fout);
    }

    public Optional<Account> getAccountById(UUID accountUuid){
        return accountRepository.findById(accountUuid);
    }

    private static String getFileExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }
}
