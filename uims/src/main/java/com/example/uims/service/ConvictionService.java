package com.example.uims.service;

import com.example.uims.entity.Conviction;
import com.example.uims.exception.NotFoundException;
import com.example.uims.repository.ConvictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvictionService {

    private final ConvictionRepository repository;

    @Autowired
    public ConvictionService(ConvictionRepository repository) {
        this.repository = repository;
    }

    public List<Conviction> getAllConvictionsByUserId(final long userId) {
        return repository.findConvictionsByUserId(userId);
    }

    public Conviction createConviction(final Conviction conviction) {
        return repository.save(conviction);
    }

    public Conviction getConvictionById(long id) {
        Optional<Conviction> conviction = repository.findById(id);
        if(conviction.isEmpty()) {
            String message = String.format("Conviction by id = %s not found", id);
            throw new NotFoundException(message);
        }
        return conviction.get();
    }
}
