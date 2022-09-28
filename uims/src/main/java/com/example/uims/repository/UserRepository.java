package com.example.uims.repository;

import com.example.uims.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByPersonalNo(String personalNo);
    Optional<User> findByPersonalNo(String personalNo);
}
