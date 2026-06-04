package com.example.Bank_Management.controller;

import com.example.Bank_Management.entity.Card;
import com.example.Bank_Management.repository.CardRepository;
import com.example.Bank_Management.service.CardService;
import com.example.Bank_Management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
private CardService  cardService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
public List<Card> getCards(){
        return cardService.getCards();
    }
    @PostMapping
    public ResponseEntity<Card> addCard(@RequestBody Card card){
        cardService.saveEntry(card);
        return ResponseEntity.ok(card);
    }

    @PreAuthorize("hasRole('CUSTOMER')or hasRole('ADMIN')")
    @GetMapping("/accountNo/{accountNumber}")
    public ResponseEntity<Card> getCardByAccountNumber(@PathVariable String accountNumber){
        Optional<Card> cardOptional = cardService.getCardbyAccountNumber(accountNumber);
            return cardOptional
                    .map(ResponseEntity::ok)
                    .orElseGet(() ->ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("cardId/{cardId}")
 public ResponseEntity<?> deleteCard(@PathVariable String cardId){
        boolean deleted =cardService.deleteCardByNo(cardId);
        if(deleted){
            return ResponseEntity.ok("Card deleted successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }
    }
    }

