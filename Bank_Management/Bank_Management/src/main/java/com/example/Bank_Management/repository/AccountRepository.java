package com.example.Bank_Management.repository;

import com.example.Bank_Management.entity.Account;
import com.example.Bank_Management.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account,String> {

 Account findByAccountNumber(String accountNumber);
 List<Account> findByCustomerId(String customerId);

}
