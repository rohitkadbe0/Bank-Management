package com.example.Bank_Management.entity;

 import jakarta.validation.constraints.NotBlank;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 import org.springframework.data.annotation.Id;
 import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "customer")
public class Customer {

    @Id
    private String  id;
@NotBlank
    private String username;
    private String email;
@NotBlank
    private String mobileNumber;
@NotBlank
    private String address;
@NotBlank
    private String aadharNumber;

    private String userId;

 }
