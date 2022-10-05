package com.example.uims.service;

import com.example.uims.entity.Admin;
import com.example.uims.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminLoginService {

    private final AdminRepository repository;

    @Autowired
    public AdminLoginService(AdminRepository repository) {
        this.repository = repository;
    }

    public Optional<Admin> login(final String username, final String password) {
        Optional<Admin> adminOptional = repository.findByUsername(username);
        if (adminOptional.isEmpty()) {
            return Optional.empty();
        } else if (!adminOptional.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return adminOptional;
    }
}
