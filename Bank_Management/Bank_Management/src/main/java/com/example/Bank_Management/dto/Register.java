package com.example.Bank_Management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    @NonNull
    private String username;
    @NonNull
    private String password;
     private String email;
     @NonNull
    private List<String> roles;
}
