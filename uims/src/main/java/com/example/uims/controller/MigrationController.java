package com.example.uims.controller;

import com.example.uims.entity.Migration;
import com.example.uims.entity.User;
import com.example.uims.service.MigrationService;
import com.example.uims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/migrations")
public class MigrationController {

    private String personalNo;
    private final MigrationService service;
    private final UserService userService;

    @Autowired
    public MigrationController(MigrationService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/user/{personalNo}")
    public String getMigrationsByUserPersonalNo(
            @PathVariable(name = "personalNo") final String personalNo,
            Model model
    ) {
        this.personalNo = personalNo;
        User user = userService.getUserByPersonalNo(personalNo).get();
        String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
        model.addAttribute("user", user);
        model.addAttribute("migrations", service.getMigrationsByUserPersonalNo(personalNo));
        model.addAttribute("personFullName", fullName);
        return "migrations";
    }

    //sorry for duplicate codes, we know it
    @GetMapping("/add-migration/{personalNo}")
    public String getAddMigrationPage(
            HttpSession session,
            @PathVariable(name = "personalNo") String personalNo,
            Model model
    ) {

        User user = userService.getUserByPersonalNo(personalNo).get();
        model.addAttribute("user", user);

        if (session.getAttribute("admin") == null) {
            String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
            model.addAttribute("migrations", service.getMigrationsByUserPersonalNo(personalNo));
            model.addAttribute("personFullName", fullName);
            model.addAttribute("noPermission", "You have not access");
            return "migrations";
        }
        this.personalNo = personalNo;
        return "add_migration";
    }

    @ModelAttribute(name = "migration")
    public Migration migration() {
        return new Migration();
    }

    @PostMapping
    public String createMigration(@ModelAttribute Migration migration) {
        Optional<User> userOptional = userService.getUserByPersonalNo(personalNo);
        migration.setUser(userOptional.get());
        service.createMigration(migration);
        return String.format("redirect:/migrations/user/%s", personalNo);
    }

    @GetMapping("/{id}")
    public Migration getMigrationById(@PathVariable long id) {
        return service.getMigrationById(id);
    }
}
