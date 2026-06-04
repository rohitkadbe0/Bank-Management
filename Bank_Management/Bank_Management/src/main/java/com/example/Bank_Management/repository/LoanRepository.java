package com.example.Bank_Management.repository;

import com.example.Bank_Management.entity.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface LoanRepository extends MongoRepository<Loan,String> {
 Optional<Loan> findByCustomerId(String customerId);
 Optional<Loan> findByCustomerName(String customerName);
 }
