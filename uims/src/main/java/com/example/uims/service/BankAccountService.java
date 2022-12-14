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

    public List<BankAccount> getAllBankAccountsByUserPersonalNo(final String personalNo) {
        return repository.findBankAccountsByUserPersonalNo(personalNo);
    }

    public BankAccount createBankAccount(final BankAccount bankAccount) {
        return repository.save(bankAccount);
    }

    public BankAccount getBankAccountByAccountNumber(String accountNumber) {
        Optional<BankAccount> bankAccount = repository.findByAccountNumber(accountNumber);
        if(bankAccount.isEmpty()) return null;
        return bankAccount.get();
    }
}
