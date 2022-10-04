package com.example.uims.controller;

import com.example.uims.entity.Migration;
import com.example.uims.entity.User;
import com.example.uims.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/migrations")
public class MigrationController {

    private final MigrationService service;

    @Autowired
    public MigrationController(MigrationService service) {
        this.service = service;
    }

    @PostMapping
    public Migration createMigration(Migration migration) {
        return service.createMigration(migration);
    }

    @GetMapping("/userMigrations/{userId}")
    public List<Migration> getMigrationsByUser(@PathVariable long userId) {
        return service.getMigrationsByUser(userId);
    }

    @GetMapping
    public Migration getMigrationById(long id) {
        return service.getMigrationById(id);
    }

}
