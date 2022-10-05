package com.example.uims.controller;

import com.example.uims.entity.User;
import com.example.uims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

//    @GetMapping
//    public Iterable<User> findAllUsers() {
//        return service.findAllUsers();
//    }

    @GetMapping
    public String viewUsers(Model model){
        List<User> users = new ArrayList<>();
        service.findAllUsers().forEach(users::add);
        model.addAttribute("allUsers", users);
        return "view_all_users";
    }

    @PostMapping
    public User createUser(@RequestBody final User user) {
        return service.createUser(user);
    }

    @GetMapping("/personalNo")
    public String getUserByPersonalNo(Model model, final String personalNo) {
        System.out.println("pers no is: " + personalNo);
        model.addAttribute("user", service.getUserByPersonalNo(personalNo));

        return "user-page";
    }

    @PutMapping
    public User updateUser(
            @RequestParam(name = "id") final long id,
            @RequestBody final User user
    ) {
        return service.updateUser(id, user);
    }

    @DeleteMapping
    public User deleteUserById(@RequestParam(name = "id") final long id) {
        return service.deleteUserById(id);
    }
}
