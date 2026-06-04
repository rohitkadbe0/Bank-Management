package com.example.Bank_Management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {
     @Autowired
    private JavaMailSender mailSender;
     public void sendEmail(String to, String accountNumber) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Card Expiry Reminder ");
        message.setText("Your card linked with account"+accountNumber+"will expire soon .Please renew it.Contect your HomeBranch");
        mailSender.send(message);
         System.out.println("Email Sent");
     }
}
