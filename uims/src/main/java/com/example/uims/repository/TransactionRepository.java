package com.example.uims.repository;

import com.example.uims.entity.BankAccount;
import com.example.uims.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Optional<Transaction> findTransactionById(long id);
    Iterable<Transaction> findTransactionsByToAccount(BankAccount account);
    Iterable<Transaction> findTransactionsByFromAccount(BankAccount account);
}
