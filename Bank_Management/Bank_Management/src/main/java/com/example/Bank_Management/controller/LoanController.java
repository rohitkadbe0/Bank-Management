package com.example.Bank_Management.controller;

import com.example.Bank_Management.entity.Loan;
import com.example.Bank_Management.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<String> Loan(@RequestBody Loan loan){
        loanService.saveEntry(loan);
        return ResponseEntity.ok("Loan Applied Successfully");
    }
@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Loan>> getLoan(){
        return ResponseEntity.ok(loanService.getLoan());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<Loan> fndByCustomerId(@PathVariable String customerId){
        return loanService.fndByCustomerId(customerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/customerName/{name}")
    public ResponseEntity<Loan> getLoanCustomerName(
            @PathVariable String name
    ){
        return loanService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
