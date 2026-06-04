package com.example.Bank_Management.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "card")
public class Card {
    @Id
    private String cardId;
    private String customerId;
    private String accountId;
    @NotBlank
    private String accountNumber;
    @NotBlank
     private String cardType;
    @Indexed(unique = true)
    private String cardNumber;
    private int cibilScore;
    @NotBlank
    private String accountType;


    private int age;
    private BigDecimal balance;
    private int salary;
    private int businessIncome;
    private LocalDate cardExpiry;
    private LocalDate createdDate;
    private String email;

}
