package com.example.uims.controller;

import com.example.uims.entity.HealthCare;
import com.example.uims.exception.NotFoundException;
import com.example.uims.service.HealthCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/health-care")
public class HealthCareController {

    private final HealthCareService service;

    @Autowired
    public HealthCareController(HealthCareService service) {
        this.service = service;
    }

    @GetMapping("/user/{personalNo}")
    public List<HealthCare> getAllHealthCareByUserPersonalNo(@PathVariable(name = "personalNo") final String personalNo) {
        return service.getAllHealthCareByUserPersonalNo(personalNo);
    }

    @PostMapping
    public HealthCare createHealthCare(@RequestBody final HealthCare healthCare) {
        return service.createHealthCare(healthCare);
    }

    @GetMapping("/{id}")
    public HealthCare getHealthCareById(@PathVariable(name = "id") long id) {
        return service.getHealthCareById(id);
    }
}
