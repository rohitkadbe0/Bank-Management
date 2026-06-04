package com.example.Bank_Management.service;

import com.example.Bank_Management.entity.Account;
import com.example.Bank_Management.repository.AccountRepository;
import com.example.Bank_Management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Account saveEntry(Account account){
        //check customer
        if(customerRepository.findById(account.getCustomerId()).isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        if(account.getAccountType()==null){
            throw new RuntimeException("Account Type is required");
        }
        if("Saving".equalsIgnoreCase(account.getAccountType())){
            if(account.getInitialbalance().compareTo(new BigDecimal("500"))<0){
                throw new RuntimeException("Minimum balance for Saving account is 500");
            }
        }
        if("Current".equalsIgnoreCase(account.getAccountType())){
            if(account.getInitialbalance()== null|| account.getInitialbalance().compareTo(new BigDecimal("5000"))<0){
                throw new RuntimeException("Minium balance for current account is 5000");
            }
        }
        String accountNumber;
        do {
            long min =10000000000L;
            long max =99999999999L;
            long number = min + (long)(Math.random() * (max - min));
            accountNumber = String.valueOf(number);

        } while(accountRepository.findByAccountNumber(accountNumber) != null);

        account.setAccountNumber(accountNumber);
         return accountRepository.save(account);
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccountByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public List<Account> getAccountByCustomerId(String customerId){
        return accountRepository.findByCustomerId(customerId);
    }

    public void deleteAccountById(String accountId){
        accountRepository.deleteById(accountId);
    }


}