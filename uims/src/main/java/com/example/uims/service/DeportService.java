package com.example.uims.service;

import com.example.uims.entity.Deport;
import com.example.uims.exception.NotFoundException;
import com.example.uims.repository.DeportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeportService {

    private final DeportRepository repository;

    @Autowired
    public DeportService(DeportRepository repository) {
        this.repository = repository;
    }

    public List<Deport> getAllDeportsByUserId(final long userId) {
        return repository.findDeportsByUserId(userId);
    }

    public Deport createDeport(final Deport deport) {
        return repository.save(deport);
    }

    public Deport getDeportById(long id) {
        Optional<Deport> deport = repository.findById(id);
        if(deport.isEmpty()) {
            String message = String.format("Deport by id = %s not found", id);
            throw new NotFoundException(message);
        }
        return deport.get();
    }
}
