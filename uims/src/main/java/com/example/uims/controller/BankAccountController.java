package com.example.uims.controller;

import com.example.uims.entity.BankAccount;
import com.example.uims.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

    private final BankAccountService service;

    @Autowired
    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public List<BankAccount> getAllBankAccountsByUserId(@PathVariable(name = "userId") final long userId) {
        return service.getAllBankAccountsByUserId(userId);
    }

    @PostMapping
    public BankAccount createBankAccount(@RequestBody final BankAccount bankAccount) {
        return service.createBankAccount(bankAccount);
    }

    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable(name = "accountNumber") final String accountNumber) {
        return service.getBankAccountByAccountNumber(accountNumber);
    }
}
