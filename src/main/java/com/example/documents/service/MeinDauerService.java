package com.example.documents.service;

import com.example.documents.exceptions.CreditCardNotFoundException;
import com.example.documents.model.Account;
import com.example.documents.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
public class MeinDauerService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CreditCardService creditCardService;

    private Random rand = new Random();

    @Transactional
    @Scheduled(fixedRate = 30000L)
    public void wirteAndShowCreditCards() {

        try {

            calculateFibonacciBlabla();
            createAndPersistCreditCard();
            deleteRichsCreditCard();
        } catch (Exception e) {
            System.out.println("Exception catched - rollback?!");
        }

    }

    private void calculateFibonacciBlabla() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //
        }
        Random rand = new Random();
        int result = rand.nextInt(10000) + rand.nextInt(10000);

        Account richardsAcc = new Account();
        richardsAcc.setVorname("Richard");
        richardsAcc.setNachname("Krasowski");
        richardsAcc.setId(UUID.randomUUID());
        richardsAcc.setTelefonNummer("015140460849");

        accountService.saveAccount(richardsAcc);
    }

    private void createAndPersistCreditCard() {
        CreditCard richsCreditCard = new CreditCard(String.valueOf(rand.ints(10000000, 100000000).findFirst().getAsInt()), "987654321", "Richard Krasowski");
        creditCardService.persistCreditCard(richsCreditCard);

    }

    private void deleteRichsCreditCard() {
        String kontoNr = "1234567";
        creditCardService.delete(kontoNr);
    }
}
