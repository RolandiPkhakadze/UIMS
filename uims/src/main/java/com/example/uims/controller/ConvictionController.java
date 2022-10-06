package com.example.uims.controller;

import com.example.uims.entity.Conviction;
import com.example.uims.entity.User;
import com.example.uims.service.ConvictionService;
import com.example.uims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/convictions")
public class ConvictionController {

    private final ConvictionService service;
    private final UserService userService;
    private String personalNo;

    @Autowired
    public ConvictionController(ConvictionService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/user/{personalNo}")
    public String getAllConvictionsByUserPersonalNo(@PathVariable(name = "personalNo") final String personalNo, Model model) {
        this.personalNo = personalNo;
        User user = userService.getUserByPersonalNo(personalNo).get();
        String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
        model.addAttribute("user", userService.getUserByPersonalNo(personalNo).get());
        model.addAttribute("personFullName", fullName);
        model.addAttribute("convictions", service.getAllConvictionsByUserPersonalNo(personalNo));
        return "convictions";
    }

    @ModelAttribute(name = "conviction")
    public Conviction conviction() {
        return new Conviction();
    }

    @GetMapping("/user/{personalNo}/add_new_conviction")
    public String getConvictionPage(@PathVariable(name = "personalNo") String personalNo) {
        this.personalNo = personalNo;
        return "add_new_conviction";
    }

    @PostMapping
    public String createConviction(@ModelAttribute final Conviction conviction) {
        Optional<User> user = userService.getUserByPersonalNo(personalNo);
        System.out.println("personal: " + personalNo);
        conviction.setUser(user.get());
        service.createConviction(conviction);
        System.out.println(String.format("redirect:/convictions/user/%s", personalNo));
        return String.format("redirect:/convictions/user/%s", personalNo);
    }

    @GetMapping("/{id}")
    public Conviction getConvictionById(@PathVariable(name = "id") long id) {
        return service.getConvictionById(id);
    }
}
