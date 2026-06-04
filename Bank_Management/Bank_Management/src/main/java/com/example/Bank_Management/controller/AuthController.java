package com.example.Bank_Management.controller;


import com.example.Bank_Management.dto.LogIn;

import com.example.Bank_Management.dto.Register;

import com.example.Bank_Management.entity.Customer;
import com.example.Bank_Management.entity.User;
import com.example.Bank_Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//login+register
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogIn request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Username or Password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registor(@RequestBody Register request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
//        user.setRoles(List.of("ROLE_USER","ROLE_ADMIN"));
        user.setRoles(request.getRoles());
        userService.register(user);
        return ResponseEntity.ok("Register Successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getall() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/name/{userName}")
    public ResponseEntity<?> getByName(@PathVariable String userName) {
        Optional<User> customer = userService.findByUserName(userName);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer Not Found");
        }

    }
}