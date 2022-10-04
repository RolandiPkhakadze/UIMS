package com.example.uims.controller;

import com.example.uims.entity.Transaction;
import com.example.uims.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/description/{id}")
    public String getDescription(@PathVariable(name = "id") long id) {
        return service.getDescription(id);
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable(name = "id") long id) {
        return service.getTransaction(id);
    }

}
