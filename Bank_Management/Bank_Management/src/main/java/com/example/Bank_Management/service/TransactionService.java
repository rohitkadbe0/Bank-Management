package com.example.Bank_Management.service;

import com.example.Bank_Management.entity.Account;
import com.example.Bank_Management.entity.Transactions;
import com.example.Bank_Management.repository.AccountRepository;
import com.example.Bank_Management.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class TransactionService {
    @Autowired
    private TransactionsRepository transactionsRepository;
@Autowired
private AccountRepository accountRepository;


    public void saveEntry(String type ,
                          String fromAccountNumber,
                          String toAccountNumber,
                          BigDecimal amount){

        if(type == null || type.isBlank()){
            throw new RuntimeException("Transaction type is required");
        }

        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Amount must be greater than zero");
        }
        if(type.equalsIgnoreCase("deposit")){

            if (toAccountNumber == null) {
                throw new RuntimeException("To Account Number is required");
            }

            Account account= accountRepository.findByAccountNumber(toAccountNumber); //this is used add another enity class in the different class
            if(account == null){
                throw new RuntimeException("Account not found");
            }
            account.setInitialbalance(
                    account.getInitialbalance().add(amount));
            accountRepository.save(account);
        }
       else if(type.equalsIgnoreCase("withdraw")){
            if (fromAccountNumber == null) {
                throw new RuntimeException("From Account Number is required");
            }

            Account account= accountRepository.findByAccountNumber(fromAccountNumber);
            if(account == null){
                throw new RuntimeException("Account not found");
            }
            if(account.getInitialbalance().compareTo(amount)<0){
                throw new RuntimeException("Insufficient Balance");
            }
            account.setInitialbalance(account.getInitialbalance().subtract(amount));
       accountRepository.save(account);
        }
        if(type.equalsIgnoreCase("transfer")){

            if (fromAccountNumber == null || toAccountNumber == null) {
                throw new RuntimeException("Both account numbers are required");
            }
            Account sender=accountRepository.findByAccountNumber(fromAccountNumber);
            Account reciver=accountRepository.findByAccountNumber(toAccountNumber);

            if(sender == null || reciver == null){
                throw new RuntimeException("Account not found");
            }

            if(sender.getInitialbalance().compareTo(amount)<0){
                throw new RuntimeException("Insufficient Balance");
            }

            sender.setInitialbalance(sender.getInitialbalance().subtract(amount));
            reciver.setInitialbalance(reciver.getInitialbalance().add(amount));

            accountRepository.save(sender);
            accountRepository.save(reciver);
        }
        Transactions transaction = new Transactions();

        transaction.setTransactionType(type);
        transaction.setFromAccountNumber(fromAccountNumber);
        transaction.setToAccountNumber(toAccountNumber);
        transaction.setAmount(amount);

        transactionsRepository.save(transaction);
    }

public List<Transactions> getAll(){
        return transactionsRepository.findAll();
}
public List<Transactions> findByFromAccountNumber(String accountNumber){
        return transactionsRepository.findByFromAccountNumber(accountNumber);
}
}
