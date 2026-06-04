package com.example.Bank_Management.repository;

import com.example.Bank_Management.entity.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByUsername(String userName);
    Optional<Customer> findById(String id);
    void deleteByUsername(String userName);
    Optional<Customer> findByAadharNumber(String aadharNumber);
 }
