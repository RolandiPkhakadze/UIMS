package com.example.uims.controller;

import com.example.uims.entity.Transaction;
import com.example.uims.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Transaction> findAllTransactions() {
        return service.findAllTransactions();
    }

    @PostMapping
    public Transaction createTransaction(Transaction transaction) {
        return service.createTransaction(transaction);
    }

    @GetMapping("/{description}")
    public String getDescription(long id) {
        return service.getDescription(id);
    }

    @GetMapping("/{Tramsaction}")
    public Transaction getTransaction(long id) {
        return service.getTransaction(id);
    }

}
