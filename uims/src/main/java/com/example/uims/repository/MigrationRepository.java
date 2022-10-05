package com.example.uims.repository;

import com.example.uims.entity.Migration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MigrationRepository extends CrudRepository<Migration, Long> {
    Optional<Migration> findMigrationById(long id);
    List<Migration> findMigrationsByUserId(long id);
}
