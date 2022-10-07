package com.example.uims.controller;

import com.example.uims.entity.Deport;
import com.example.uims.entity.Migration;
import com.example.uims.entity.User;
import com.example.uims.service.DeportService;
import com.example.uims.service.MigrationService;
import com.example.uims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Optional;

@Controller
@RequestMapping("/deports")
public class DeportController {

    private String personalNo;
    private final DeportService service;
    private final UserService userService;
    private final MigrationService migrationService;

    @Autowired
    public DeportController(DeportService service, UserService userService, MigrationService migrationService) {
        this.service = service;
        this.userService = userService;
        this.migrationService = migrationService;
    }

    @GetMapping("/user/{personalNo}")
    public String getAllDeportByUserPersonalNo(
            @PathVariable(name = "personalNo") final String personalNo,
            Model model
    ) {
        this.personalNo = personalNo;
        User user = userService.getUserByPersonalNo(personalNo).get();
        String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
        model.addAttribute("user", userService.getUserByPersonalNo(personalNo).get());
        model.addAttribute("deports", service.getAllDeportsByUserPersonalNo(personalNo));
        model.addAttribute("personFullName", fullName);
        return "deports";
    }

    //sorry for duplicate codes, we know it
    @GetMapping("/add-deport/{personalNo}")
    public String getAddDeportPage(
            HttpSession session,
            @PathVariable(name = "personalNo") String personalNo,
            Model model
    ) {
        if (session.getAttribute("admin") == null) {
            User user = userService.getUserByPersonalNo(personalNo).get();
            String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
            model.addAttribute("user", userService.getUserByPersonalNo(personalNo).get());
            model.addAttribute("deports", service.getAllDeportsByUserPersonalNo(personalNo));
            model.addAttribute("personFullName", fullName);
            model.addAttribute("noPermission", "You have not access");
            return "deports";
        }
        this.personalNo = personalNo;
        return "add_new_deport";
    }

    @ModelAttribute("deport")
    public Deport deport() {
        return new Deport();
    }

    @PostMapping
    public String createDeport(
            @ModelAttribute Deport deport,
            @RequestParam(name = "date") Date date
    ) {
        Optional<Migration> migrationOptional = migrationService.findMigrationByDateAndUserPersonalNo(date, personalNo);
        if (migrationOptional.isEmpty()) {
            return String.format("redirect:/deports/add-deport/%s", personalNo);
        }
        deport.setMigration(migrationOptional.get());
        service.createDeport(deport);
        return String.format("redirect:/deports/user/%s", personalNo);
    }

    @GetMapping("/{id}")
    public Deport getDeportById(@PathVariable(name = "id") long id) {
        return service.getDeportById(id);
    }
}
