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
    public Migration createMigration(@RequestBody Migration migration) {
        return service.createMigration(migration);
    }

    @GetMapping("/userMigrations/{personalNo}")
    public List<Migration> getMigrationsByUserPersonalNo(@PathVariable final String personalNo) {
        return service.getMigrationsByUserPersonalNo(personalNo);
    }

    @GetMapping("/{id}")
    public Migration getMigrationById(@PathVariable long id) {
        return service.getMigrationById(id);
    }

}
