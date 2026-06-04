package com.example.Bank_Management.repository;

import com.example.Bank_Management.entity.Account;
import com.example.Bank_Management.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CardRepository extends MongoRepository<Card,String> {
    Optional<Card> findByAccountNumber(String accountNumber);
    void deleteByCardNumber(String cardNumber);
}
