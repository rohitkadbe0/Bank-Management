package com.example.Bank_Management.controller;

import com.example.Bank_Management.entity.Customer;
import com.example.Bank_Management.repository.CustomerRepository;
import com.example.Bank_Management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Customer> getAllCustomer()
    {
        return customerService.getAll();
    }
    @PreAuthorize("hasRole('ADMIN')or hasRole('CUSTOMER')")
    @GetMapping("/name/{userName}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String userName) {
        Optional<Customer> customer = customerService.findByName(userName);

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with name '" + userName + "' not found");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with id '" + id + "' not found");
        }
    }


    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        customerService.saveEntry(customer);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/username/{username}")
    public ResponseEntity<?> updateCustomer(@PathVariable String userName,
                                            @RequestBody Customer customer) {
        try {
            // Find the customer by name
            Optional<Customer> optionalCustomer = customerService.findByName(userName);

            if (optionalCustomer.isPresent()) {
                Customer existingCustomer = optionalCustomer.get();

                // Update only the fields provided in request body
                if (customer.getEmail() != null) {
                    existingCustomer.setEmail(customer.getEmail());
                }
                if (customer.getMobileNumber() != null) {
                    existingCustomer.setMobileNumber(customer.getMobileNumber());
                }
                if (customer.getAddress() != null) {
                    existingCustomer.setAddress(customer.getAddress());
                }

                // Do NOT update the name field
                // existingCustomer.setName(customer.getName());

                // Save the updated customer
                customerService.saveEntry(existingCustomer);

                return ResponseEntity.ok(existingCustomer);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Customer with name '" + userName + "' not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating customer: " + e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/username/{username}")
 public ResponseEntity<?> deleteByUsername(@PathVariable String userName) {
    boolean deleted = customerService.deleteByName(userName);

    if (deleted) {
        return ResponseEntity.ok("Customer deleted successfully");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Customer with name " + userName + " not found");
    }
}
    @PreAuthorize("hasRole('ADMIN')or hasRole('CUSTOMER')")
@GetMapping("/aadharNumber/{aadharNumber}")
public ResponseEntity<?> getbyAadharNumber(@PathVariable String aadharNumber) {

    Optional<Customer> customer =
            customerService.findByAadharNumber(aadharNumber);

    if (customer.isPresent()) {
        return ResponseEntity.ok(customer.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Customer with aadharNumber " + aadharNumber + " not found");
    }
}
}
