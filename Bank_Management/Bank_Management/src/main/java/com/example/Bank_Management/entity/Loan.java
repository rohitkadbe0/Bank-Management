package com.example.Bank_Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    private String loanId;
    private String customerId;
    private String customerName;
    private String loanType;// home,personal,car,education
    private BigDecimal loanAmount;
    private int loanTenure;// months or years
    private BigDecimal annualIncome;
    private String status;
    private LocalDate appliedDate;
}
