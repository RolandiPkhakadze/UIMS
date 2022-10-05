package com.example.uims.service;

import com.example.uims.entity.HealthCare;
import com.example.uims.exception.NotFoundException;
import com.example.uims.repository.HealthCareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthCareService {

    private final HealthCareRepository repository;

    @Autowired
    public HealthCareService(HealthCareRepository repository) {
        this.repository = repository;
    }

    public List<HealthCare> getAllHealthCareByUserPersonalNo(final String personalNo) {
        return repository.findHealthCaresByUserPersonalNo(personalNo);
    }

    public HealthCare createHealthCare(final HealthCare healthCare) {
        return repository.save(healthCare);
    }

    public HealthCare getHealthCareById(long id) {
        Optional<HealthCare> healthCare = repository.findById(id);
        if(healthCare.isEmpty()) {
            String message = String.format("HealthCare by id = %s not found", id);
            throw new NotFoundException(message);
        }
        return healthCare.get();
    }
}
