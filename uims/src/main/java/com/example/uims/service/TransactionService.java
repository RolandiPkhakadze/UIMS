package com.example.uims.service;

import com.example.uims.entity.Transaction;
import com.example.uims.exception.NotFoundException;
import com.example.uims.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    @Autowired
    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Iterable<Transaction> findAllTransactions() {
        return repository.findAll();
    }

    public Transaction getTransaction(long id) {
        Optional<Transaction> transactionOptional = repository.findTransactionById(id);
        checkOptional(transactionOptional, id);
        return transactionOptional.get();
    }

    public String getDescription(long id)  {
        return getTransaction(id).getDescription();
    }


    public Transaction createTransaction(final Transaction transaction) {
        return repository.save(transaction);
    }

    private void checkOptional(Optional<Transaction> optional, long id) {
        if(optional.isEmpty()){
            String message = String.format("Transaction with id = %s not found", id);
            throw new NotFoundException(message);
        }
    }
}
