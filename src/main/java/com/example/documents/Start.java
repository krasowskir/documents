package com.example.documents;

import com.example.documents.model.Account;
import com.example.documents.model.Identity;
import com.example.documents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class Start {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void addUsersToDb(){

        UUID testUuid = UUID.fromString("c27a0ae4-26c6-4cfb-8812-761a55cc611b");
        System.out.println("---testuuid:---" + testUuid);
        Identity richard = new Identity();
        richard.setUsername("richard");
        richard.setPassword("test1234");

        Identity toni = new Identity();
        toni.setUsername("toni");
        toni.setPassword("flusensieb");

        Account account = new Account();
        account.setName("Richar");
        account.setAlter(27);
        account.setTelefonNummer("0151404060849");
        account.setEmailAdresse("testi-test@arcor.de");
        account.setId(testUuid);

        richard.setAccount(account);

        userService.saveUser(richard);
        userService.saveUser(toni);

        for (Identity elem : userService.findAllUsers()){
            System.out.println("Identity: " + elem);
        }
    }
}
