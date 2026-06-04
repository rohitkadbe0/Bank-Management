package com.example.Bank_Management.repository;

import com.example.Bank_Management.entity.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionsRepository extends MongoRepository<Transactions,String> {
    List<Transactions> findByFromAccountNumber(String accountNumber);

}
