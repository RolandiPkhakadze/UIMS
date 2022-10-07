package com.example.uims.controller;

import com.example.uims.entity.BankAccount;
import com.example.uims.entity.Migration;
import com.example.uims.entity.User;
import com.example.uims.service.BankAccountService;
import com.example.uims.service.UserService;
import com.sun.source.tree.OpensTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bank-accounts")
public class BankAccountController {

    private final BankAccountService service;
    private String personalNo;
    private final UserService userService;
    @Autowired
    public BankAccountController(BankAccountService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/user/{personalNo}")
    public String getAllBankAccountsByUserPersonalNo(
            @PathVariable(name = "personalNo") final String personalNo,
            Model model) {

        this.personalNo = personalNo;
        User user = userService.getUserByPersonalNo(personalNo).get();
        String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
        model.addAttribute("accounts", service.getAllBankAccountsByUserPersonalNo(personalNo));
        model.addAttribute("personFullName", fullName);
        model.addAttribute("user", userService.getUserByPersonalNo(personalNo).get());
        return "bank_accounts";
    }

    @GetMapping("/add-bank-account/{personalNo}")
    public String getAddBankAccountPage(
            @PathVariable(name = "personalNo") String personalNo,
            HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return String.format("redirect:/bank-accounts/user/%s", personalNo);
        }
        this.personalNo = personalNo;
        return "add_new_bank_account";
    }

    @ModelAttribute(name = "bankAccount")
    public BankAccount bankAccount() {
        return new BankAccount();
    }

    @PostMapping
    public String createBankAccount(@ModelAttribute final BankAccount bankAccount) {
        System.out.println("acc num: " + bankAccount.getAccountNumber());
        Optional<User> userOptional = userService.getUserByPersonalNo(personalNo);
        bankAccount.setUser(userOptional.get());
        System.out.println("acc num: " + bankAccount.getAccountNumber());
        service.createBankAccount(bankAccount);
        return String.format("redirect:/bank-accounts/user/%s", personalNo);
    }

    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable(name = "accountNumber") final String accountNumber) {
        return service.getBankAccountByAccountNumber(accountNumber);
    }
}
