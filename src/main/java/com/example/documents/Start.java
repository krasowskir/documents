package com.example.documents;

import com.example.documents.model.Account;
import com.example.documents.model.Identity;
import com.example.documents.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Component
public class Start {

    @Autowired
    UserService userService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment env;

    @Autowired
    private ObjectMapper objectMapper;

    // new ClassPathResource("testBilder/zweites_avatar_2.png"); geht auch, aber dann muss testBilder unter
    // src/main/resources liegen

    Resource meinBild = new DefaultResourceLoader().getResource("file:./testBilder/zweites_avatar_2.png");

    @PostConstruct
    public void addUsersToDb() throws IOException {

        UUID testUuid = UUID.fromString("c27a0ae4-26c6-4cfb-8812-761a55cc611b");
        System.out.println("---testuuid:---" + testUuid);

        Identity richard = new Identity();
        richard.setUsername("richard");
        richard.setPassword("test1234");

        Identity toni = new Identity();
        toni.setUsername("toni");
        toni.setPassword("flusensieb");

        Account account = new Account();
        account.setName("Richard");
        account.setAlter(27);
        account.setTelefonNummer("0151404060849");
        account.setEmailAdresse("testi-test@arcor.de");
        account.setId(testUuid);
        byte[] imageBytes = Files.readAllBytes(meinBild.getFile().toPath());
        System.out.println("bytes: " + imageBytes);
        account.setImage(imageBytes);
        richard.setAccount(account);

        System.out.println(objectMapper.writeValueAsString(account));
        System.out.println("\n===environment: === " + env.getProperty("spring.datasource.url"));

        userService.saveUser(richard);
        userService.saveUser(toni);
    }


}
