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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //sorry for duplicate codes, we know it
    @GetMapping("/add-bank-account/{personalNo}")
    public String getAddBankAccountPage(
            @PathVariable(name = "personalNo") String personalNo,
            HttpSession session,
            Model model) {

        User user = userService.getUserByPersonalNo(personalNo).get();
        model.addAttribute("user", user);

        if (session.getAttribute("admin") == null) {
            String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
            model.addAttribute("accounts", service.getAllBankAccountsByUserPersonalNo(personalNo));
            model.addAttribute("personFullName", fullName);
            model.addAttribute("noPermission", "You have not access");
            return "bank_accounts";
        }
        this.personalNo = personalNo;
        return "add_new_bank_account";
    }

    @ModelAttribute(name = "bankAccount")
    public BankAccount bankAccount() {
        return new BankAccount();
    }

    @PostMapping
    public String createBankAccount(@ModelAttribute final BankAccount bankAccount, Model model) {
        if(!validBankAccount(bankAccount)){
            model.addAttribute("errorMessage", "Account Number is invalid or already taken!");
            return "add_new_bank_account";
        }
        Optional<User> userOptional = userService.getUserByPersonalNo(personalNo);
        bankAccount.setUser(userOptional.get());
        System.out.println("acc num: " + bankAccount.getAccountNumber());
        service.createBankAccount(bankAccount);
        return String.format("redirect:/bank-accounts/user/%s", personalNo);
    }

    private boolean validBankAccount(BankAccount bankAccount) {
        String regex = "\\d{12}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(bankAccount.getAccountNumber());

        return m.matches() && (service.getBankAccountByAccountNumber(bankAccount.getAccountNumber()) == null);
    }

    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable(name = "accountNumber") final String accountNumber) {
        return service.getBankAccountByAccountNumber(accountNumber);
    }
}
