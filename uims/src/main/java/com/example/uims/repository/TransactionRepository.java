package com.example.uims.repository;

import com.example.uims.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long > {

    @Override
    boolean existsById( Long id);
    Optional<Transaction> findTransactionById(Long id);
}
