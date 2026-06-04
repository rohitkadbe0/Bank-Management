package com.example.Bank_Management.controller;

import com.example.Bank_Management.entity.Transactions;
import com.example.Bank_Management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping
    public ResponseEntity<String> transaction(@RequestBody Transactions transactions){
         transactionService.saveEntry(transactions.getTransactionType(),
                 transactions.getFromAccountNumber(),
                 transactions.getToAccountNumber(),
                 transactions.getAmount());
         return ResponseEntity.ok("transactions Successful");
         
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAll());
    }
    @GetMapping("/from/{accountNumber}")
    public ResponseEntity<List<Transactions>> getFromAccount(
            @PathVariable String accountNumber){

        return ResponseEntity.ok(
                transactionService.findByFromAccountNumber(accountNumber));
    }
}
