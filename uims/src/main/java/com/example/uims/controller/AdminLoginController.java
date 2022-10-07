package com.example.uims.controller;

import com.example.uims.entity.Admin;
import com.example.uims.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class
AdminLoginController {

    private final AdminLoginService service;

    @Autowired
    public AdminLoginController(AdminLoginService service) {
        this.service = service;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(
            Model model,
            HttpSession session,
            @RequestParam(value = "username") final String username,
            @RequestParam(value = "password") final String password
    ) {
        Optional<Admin> adminOptional = service.login(username, password);
        if (adminOptional.isEmpty()){
            String errorMessage = "You are not an Admin!";
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        }
        session.setAttribute("admin", adminOptional.get());
        return "redirect:/users";
    }
}
