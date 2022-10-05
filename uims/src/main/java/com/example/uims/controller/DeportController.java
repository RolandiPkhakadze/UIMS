package com.example.uims.controller;

import com.example.uims.entity.Deport;
import com.example.uims.service.DeportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deports")
public class DeportController {

    private final DeportService service;

    @Autowired
    public DeportController(DeportService service) {
        this.service = service;
    }

    @GetMapping("/user/{personalNo}")
    public List<Deport> getAllDeportByUserPersonalNo(@PathVariable(name = "personalNo") final String personalNo) {
        return service.getAllDeportsByUserPersonalNo(personalNo);
    }

    @PostMapping
    public Deport createDeport(@RequestBody final Deport deport) {
        return service.createDeport(deport);
    }

    @GetMapping("/{id}")
    public Deport getDeportById(@PathVariable(name = "id") long id) {
        return service.getDeportById(id);
    }
}
