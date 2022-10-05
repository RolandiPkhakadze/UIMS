package com.example.uims.repository;

import com.example.uims.entity.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
    List<BankAccount> findBankAccountsByUserPersonalNo(String personalNo);
    Optional<BankAccount> findByAccountNumber(String accountNumber);
}
