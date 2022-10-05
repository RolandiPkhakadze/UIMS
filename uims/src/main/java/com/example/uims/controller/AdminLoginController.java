package com.example.uims.controller;

import com.example.uims.entity.Admin;
import com.example.uims.exception.NotFoundException;
import com.example.uims.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AdminLoginController {

    private final AdminLoginService service;

    @Autowired
    public AdminLoginController(AdminLoginService service) {
        this.service = service;
    }

    @PostMapping
    public Admin login(@RequestParam final String username, @RequestParam final String password) {
        return service.login(username, password)
                .orElseThrow(() -> new NotFoundException("Admin not found"));
    }
}
