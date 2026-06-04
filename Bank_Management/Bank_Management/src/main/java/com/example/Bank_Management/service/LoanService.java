package com.example.Bank_Management.service;

import com.example.Bank_Management.entity.Loan;
import com.example.Bank_Management.repository.CustomerRepository;
import com.example.Bank_Management.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service

public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
@Autowired
private CustomerRepository customerRepository;
public void saveEntry(Loan loan){

    BigDecimal amount=loan.getLoanAmount();

    if(customerRepository.findById(loan.getCustomerId()).isEmpty()){
        throw new RuntimeException("Customer not Found");
    }
    if(loan.getLoanType()==null){
        throw new RuntimeException("loan type is required!!");
    }
    if(loan.getLoanAmount()==null||loan.getAnnualIncome().compareTo(BigDecimal.ZERO) <= 0){
        throw new RuntimeException("Loan amount is required or invalid !!");
    }
    if(loan.getAnnualIncome()==null||loan.getAnnualIncome().compareTo(BigDecimal.ZERO)<=0){
        throw new RuntimeException("Annual Income is required or invalid!!");
    }

    if("Personal loan".equalsIgnoreCase(loan.getLoanType())){
        if(loan.getAnnualIncome().compareTo(new BigDecimal("300000"))<0){
            throw new RuntimeException("You are not validate for the personal loan");
        }

    }
    if("Car loan".equalsIgnoreCase(loan.getLoanType())){
        if(loan.getAnnualIncome().compareTo(new BigDecimal("500000"))<0){
            throw new RuntimeException("You are not validate for the Car loan");
        }
    }
if("Home loan".equalsIgnoreCase(loan.getLoanType())){
    if(loan.getAnnualIncome().compareTo(new BigDecimal("800000"))<0){
        throw new RuntimeException("You are not validate for the Home loan");
    }
}
if("Education loan".equalsIgnoreCase(loan.getLoanType())){
    if(loan.getAnnualIncome().compareTo(new BigDecimal("200000"))<0){
        throw new RuntimeException("you are not validate for the Education loan");
    }
}
if(amount.compareTo(new BigDecimal("50000"))<=0){
    loan.setLoanTenure(12);
}
else if (amount.compareTo(new BigDecimal("200000"))<=0) {
    loan.setLoanTenure(24);
}
else if (amount.compareTo(new BigDecimal("500000"))<=0) {
    loan.setLoanTenure(36);

}
else if (amount.compareTo(new BigDecimal("1000000"))<=0) {
    loan.setLoanTenure(60);
}
else {
    loan.setLoanTenure(120);
}
    loan.setStatus("PENDING");
loan.setAppliedDate(LocalDate.now());
    loanRepository.save(loan);
}

public List<Loan>getLoan(){
    return loanRepository.findAll();
}
public Optional<Loan> fndByCustomerId(String customerId){
    return loanRepository.findByCustomerId(customerId);
}
public Optional<Loan>findByName(String name){
    return loanRepository.findByCustomerName(name);
}
}
