package com.example.Bank_Management.controller;

import com.example.Bank_Management.entity.Account;
import com.example.Bank_Management.service.AccountService;
import com.example.Bank_Management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/accountNumber/{accountNumber}")
    public Account getAccount(@PathVariable("accountNumber") String accountNumber) {
        return accountService.getAccountByAccountNumber(accountNumber);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        accountService.saveEntry(account);
        return account;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customerId/{customerId}")
    public List<Account> getAccountByCustomerId(@PathVariable("customerId") String customerId) {
        return accountService.getAccountByCustomerId(customerId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{accountId}")
    public void deleteAccountById(@PathVariable("accountId") String accountId) {
        accountService.deleteAccountById(accountId);
    }

}
