package com.example.uims.controller;

import com.example.uims.entity.User;
import com.example.uims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String viewUsers(Model model, HttpSession session){
        if (session.getAttribute("admin") == null) {
            return "index";
        }
        List<User> users = new ArrayList<>();
        service.findAllUsers().forEach(users::add);
        model.addAttribute("allUsers", users);
        return "view_all_users";
    }

    @GetMapping("/add-user")
    public String getAddUserPage(HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "index";
        }
        return "add_new_user";
    }

    @ModelAttribute(name = "user")
    public User user() {
        return new User();
    }

    @PostMapping
    public String createUser(@ModelAttribute final User user, Model model) {
        if(!isValidPersonalNo(user.getPersonalNo())){
            model.addAttribute("errorMessage", "Not Valid Personal Number!");
            return "add_new_user";
        }
        Optional<User> userOptional = service.createUser(user);
        if (userOptional.isEmpty() ) {
            return "add_new_user";
        }



        return "redirect:/users";
    }

    private boolean isValidPersonalNo(String personalNo) {
        String regex = "\\d{11}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(personalNo);
        return m.matches();
    }

    @GetMapping("/personalNo")
    public String getUserByPersonalNo(Model model, final String personalNo) {
        Optional<User> userOptional = service.getUserByPersonalNo(personalNo);

        if(userOptional.isEmpty()){
            String message = "User with given Personal No doesn't exists";
            model.addAttribute("errorMessage", message);
            return "index";
        }
        model.addAttribute("user", userOptional.get());
        String fullName = String.format("%s %s", userOptional.get().getFirstName(), userOptional.get().getLastName());
        model.addAttribute("personFullName", fullName);
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
