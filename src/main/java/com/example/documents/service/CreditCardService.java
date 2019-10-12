package com.example.documents.service;

import com.example.documents.exceptions.CreditCardNotFoundException;
import com.example.documents.model.CreditCard;
import com.example.documents.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard fetchCreditCard(String id) {
        if (creditCardRepository.existsById(id)) {
            return creditCardRepository.findById(id).get();
        } else {
            throw new CreditCardNotFoundException(String.format("creditCard with id %s not found", id));
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void persistCreditCard(CreditCard creditCard){
        creditCardRepository.save(creditCard);
    }

    @Transactional
    public void delete(String id) throws CreditCardNotFoundException{
        if (creditCardRepository.existsById(id)) {
            creditCardRepository.delete(creditCardRepository.findById(id).get());
        } else {
            throw new CreditCardNotFoundException(String.format("creditCard with id %s not found", id));
        }
    }
}
