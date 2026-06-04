package com.example.Bank_Management.service;

import com.example.Bank_Management.entity.Customer;
 import com.example.Bank_Management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void saveEntry(Customer customer){
        customerRepository.save(customer);
    }
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    public Optional<Customer> findByName(String name){
        return customerRepository.findByUsername(name);
    }
    public boolean deleteByName(String name){
        Optional<Customer> customer = customerRepository.findByUsername(name);

        if (customer.isPresent()) {
            customerRepository.deleteByUsername(name);
            return true;
        }
        return false;
    }
    public Optional<Customer> findById(String id) {
        return customerRepository.findById(id);
    }
    public Optional<Customer> findByAadharNumber(String aadharNumber){
        return customerRepository.findByAadharNumber(aadharNumber);
    }

}
