package com.example.uims.repository;

import com.example.uims.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long > {

    boolean existsById(long id);
    Optional<Transaction> findTransactionById(long id);
}
