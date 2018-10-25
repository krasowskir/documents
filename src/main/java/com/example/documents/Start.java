package com.example.documents;

import com.example.documents.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class Start {

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void showRichard() throws JsonProcessingException {
        Account richard = new Account();
        richard.setVorname("Richard");
        richard.setNachname("Krasowski");
        richard.setEmailAdresse("testi@test.de");
        richard.setTelefonNummer("123456789");
        richard.setId(UUID.randomUUID());

        System.out.println(objectMapper.writeValueAsString(richard));
    }
}
