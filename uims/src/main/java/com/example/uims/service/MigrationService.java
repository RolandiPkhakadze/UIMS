package com.example.uims.service;

import com.example.uims.entity.Migration;
import com.example.uims.entity.User;
import com.example.uims.exception.NotFoundException;
import com.example.uims.repository.MigrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MigrationService {

    private final MigrationRepository repository;

    @Autowired
    public MigrationService(MigrationRepository repository) {
        this.repository = repository;
    }

    public Migration createMigration(final Migration migration) {
        return repository.save(migration);
    }

    public List<Migration> getMigrationsByUserPersonalNo(final String personalNo) {
        return repository.findMigrationsByUserPersonalNo(personalNo);
    }

    public Optional<Migration> findMigrationByDateAndUserPersonalNo(final Date date, final String personalNo) {
        Optional<Migration> migration = repository.findMigrationByDateAndUserPersonalNo(date, personalNo);
        if (migration.isEmpty()) {
            return Optional.empty();
        }
        return migration;
    }

    public Migration getMigrationById(long id) {
        Optional<Migration> migration = repository.findMigrationById(id);
        if(migration.isEmpty()) {
            String message = String.format("Migration by id = %s not found", id);
            throw new NotFoundException(message);
        }

        return migration.get();
    }

}
