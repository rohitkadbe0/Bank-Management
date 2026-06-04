package com.example.Bank_Management.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "account")
public class Account {

        @Id
        private String accountId;
        @NotBlank
         private String accountType;
        @NotBlank
        private BigDecimal Initialbalance;
        @Indexed(unique=true)
        private String accountNumber;

        private String customerId;

}
