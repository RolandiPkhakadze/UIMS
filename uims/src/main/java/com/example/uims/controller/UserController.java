package com.example.uims.controller;

import com.example.uims.entity.User;
import com.example.uims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<User> findAllUsers() {
        return service.findAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody final User user) {
        return service.createUser(user);
    }

    @GetMapping("/{personalNo}")
    public User getUserByPersonalNo(@PathVariable(name = "personalNo") final String personalNo) {
        return service.getUserByPersonalNo(personalNo);
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
