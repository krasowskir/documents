package com.example.documents.repository;

import com.example.documents.model.CreditCard;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepository extends CrudRepository<CreditCard, String> {
}
