package com.example.documents;

import com.example.documents.model.Identity;
import com.example.documents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Start {

    @Autowired
    UserService userService;

    @PostConstruct
    public void addUsersToDb(){

        Identity richard = new Identity();
        richard.setUsername("richard");
        richard.setPassword("test1234");

        Identity toni = new Identity();
        toni.setUsername("toni");
        toni.setPassword("flusensieb");

        userService.saveUser(richard);
        userService.saveUser(toni);

        for (Identity elem : userService.findAllUsers()){
            System.out.println("Identity: " + elem);
        }
    }
}
