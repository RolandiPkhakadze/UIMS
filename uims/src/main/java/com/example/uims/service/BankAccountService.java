package com.example.uims.service;

import com.example.uims.entity.BankAccount;
import com.example.uims.exception.NotFoundException;
import com.example.uims.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository repository;

    @Autowired
    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public List<BankAccount> getAllBankAccountsByUserId(final long userId) {
        return repository.findBankAccountsByUserId(userId);
    }

    public BankAccount createBankAccount(final BankAccount bankAccount) {
        return repository.save(bankAccount);
    }

    public BankAccount getBankAccountByAccountNumber(String accountNumber) {
        Optional<BankAccount> bankAccount = repository.findByAccountNumber(accountNumber);
        if(bankAccount.isEmpty()) {
            String message = String.format("Bank Account by account number = %s not found", accountNumber);
            throw new NotFoundException(message);
        }
        return bankAccount.get();
    }
}
