package com.example.uims.controller;

import com.example.uims.entity.HealthCare;
import com.example.uims.entity.User;
import com.example.uims.service.HealthCareService;
import com.example.uims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/health-care")
public class HealthCareController {

    private String personalNo;
    private final HealthCareService service;
    private final UserService userService;

    @Autowired
    public HealthCareController(HealthCareService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/user/{personalNo}")
    public String getAllHealthCareByUserPersonalNo(
            @PathVariable(name = "personalNo") final String personalNo,
            Model model
    ) {
        this.personalNo = personalNo;
        User user = userService.getUserByPersonalNo(personalNo).get();
        String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
        model.addAttribute("healthCares", service.getAllHealthCareByUserPersonalNo(personalNo));
        model.addAttribute("personFullName", fullName);
        model.addAttribute("user", userService.getUserByPersonalNo(personalNo).get());
        return "health_care";
    }

    @GetMapping("/add-health-care/{personalNo}")
    public String getAddHealthCarePage(@PathVariable(name = "personalNo") String personalNo) {
        this.personalNo = personalNo;
        return "add_new_healthcare";
    }

    @ModelAttribute(name = "healthCare")
    public HealthCare healthCare() {
        return new HealthCare();
    }

    @PostMapping
    public String createHealthCare(@ModelAttribute HealthCare healthCare) {
        Optional<User> userOptional = userService.getUserByPersonalNo(personalNo);
        healthCare.setUser(userOptional.get());
        service.createHealthCare(healthCare);
        return String.format("redirect:/health-care/user/%s", personalNo);
    }

    @GetMapping("/{id}")
    public HealthCare getHealthCareById(@PathVariable(name = "id") long id) {
        return service.getHealthCareById(id);
    }
}
