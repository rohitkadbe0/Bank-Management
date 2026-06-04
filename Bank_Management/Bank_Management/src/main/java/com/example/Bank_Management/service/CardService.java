package com.example.Bank_Management.service;

import com.example.Bank_Management.entity.Card;
import com.example.Bank_Management.entity.Customer;
import com.example.Bank_Management.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public void saveEntry(Card card){

        if(card.getCardType().equalsIgnoreCase("Debit")){

            if(card.getAccountType().equalsIgnoreCase("Saving")){
                if(card.getAge() < 18 || card.getBalance().doubleValue() < 1000){
                    throw new RuntimeException("Not eligible for Debit Card");
                }
            }

            if(card.getAccountType().equalsIgnoreCase("Current")){
                if(card.getAge() < 18 || card.getBalance().doubleValue() < 5000){
                    throw new RuntimeException("Not eligible for Debit Card");
                }
            }
        }

        if(card.getCardType().equalsIgnoreCase("Credit")){

            if(card.getSalary() < 20000 || card.getCibilScore() < 700){
                throw new RuntimeException("Not eligible for Credit Card");
            }
        }

        else if(card.getCardType().equalsIgnoreCase("Premium Credit")){

            if(card.getBusinessIncome() < 30000 || card.getCibilScore() < 700){
                throw new RuntimeException("Not eligible for Premium Credit Card");
            }
        }
        String cardNumber;
        do{
            long min =100000000000L;
            long max =999999999999L;
            long number=min+(long)(Math.random()*(max-min));
            cardNumber=String.valueOf(number);
        }
        while(cardRepository.findById(cardNumber).isPresent());
         card.setCardNumber(cardNumber);
         card.setCreatedDate(LocalDate.now());
        card.setCardExpiry(card.getCreatedDate().plusYears(7));
        cardRepository.save(card);
    }
    public List<Card> getCards(){
        return cardRepository.findAll();
    }
    public Optional<Card> getCardbyAccountNumber(String accountNumber){
        return cardRepository.findByAccountNumber(accountNumber);
    }

    public boolean deleteCardByNo(String card1){
        Optional<Card> card = cardRepository.findById(card1);
        if(card.isPresent()){
            cardRepository.deleteByCardNumber(card1);
            return true;
        }
        return false;
    }
}
