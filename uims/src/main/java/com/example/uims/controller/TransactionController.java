package com.example.uims.controller;

import com.example.uims.entity.BankAccount;
import com.example.uims.entity.Transaction;
import com.example.uims.entity.User;
import com.example.uims.service.BankAccountService;
import com.example.uims.service.TransactionService;
import com.example.uims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;
    private final UserService userService;
    private final BankAccountService bankAccountService;
    private String personalNo;
    @Autowired
    public TransactionController(TransactionService service, UserService userService, BankAccountService bankAccountService) {
        this.service = service;
        this.userService = userService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public Iterable<Transaction> findAllTransactions() {
        return service.findAllTransactions();
    }

    @GetMapping("/user/{personalNo}")
    public String getAllTransactionsByPersonalNo(
            @PathVariable(name = "personalNo") final String personalNo,
            Model model){
        this.personalNo = personalNo;
        List<BankAccount> bankAccounts = bankAccountService.getAllBankAccountsByUserPersonalNo(personalNo);
        List<Transaction> incomeTransactions = new ArrayList<>();
        List<Transaction> outgoingTransactions = new ArrayList<>();

        for(BankAccount account: bankAccounts){
            incomeTransactions.addAll(service.getAllIncomeTransactionsByBankAccount(account));
            outgoingTransactions.addAll(service.getAllOutgoingTransactionsByBankAccount(account));
        }

        User user = userService.getUserByPersonalNo(personalNo).get();
        String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
        model.addAttribute("user", userService.getUserByPersonalNo(personalNo).get());
        model.addAttribute("personFullName", fullName);
        model.addAttribute("income", incomeTransactions);
        model.addAttribute("outgoing", outgoingTransactions);

        return "transactions";
    }


    @ModelAttribute
    public Transaction transaction() {
        return new Transaction();
    }

    @GetMapping("/add_new_transaction/{personalNo}")
    public String getAddTransactionPage(
            HttpSession session,
            Model model,
            @PathVariable(name = "personalNo") String personalNo
    ) {
        if (session.getAttribute("admin") == null) {
            model.addAttribute("noPermission", "You have not access");
            return String.format("redirect:/convictions/user/%s", personalNo);
        }
        this.personalNo = personalNo;
        return "add_transaction";
    }

    @PostMapping
    public String createTransaction(@ModelAttribute final Transaction transaction, Model model, HttpServletRequest request) {
        String fromAccNum = request.getParameter("from");
        String toAccNum = request.getParameter("to");
        BankAccount fromAcc = bankAccountService.getBankAccountByAccountNumber(fromAccNum);
        BankAccount toAcc = bankAccountService.getBankAccountByAccountNumber(toAccNum);
        List<BankAccount> userAccounts = bankAccountService.getAllBankAccountsByUserPersonalNo(personalNo);

        if(validBankAccounts(fromAcc, toAcc, userAccounts)){
            transaction.setFromAccount(fromAcc);
            transaction.setToAccount(toAcc);
            service.createTransaction(transaction);
            return String.format("redirect:/transactions/user/%s", personalNo);
        }

        String errorMessage = "Given Account Numbers are invalid, they are same, or none of them belongs to the User!";
        model.addAttribute("errorMessage", errorMessage);
        return "add_transaction";
    }

    private boolean validBankAccounts(BankAccount fromAcc, BankAccount toAcc, List<BankAccount> userAccounts) {
        if(fromAcc == null || toAcc == null) return false;
        return (userAccounts.contains(fromAcc) ||
                userAccounts.contains(toAcc)) && !(toAcc.getAccountNumber().equals(fromAcc.getAccountNumber()));
    }

    @GetMapping("/description/{id}")
    public String getDescription(@PathVariable(name = "id") long id) {
        return service.getDescription(id);
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable(name = "id") long id) {
        return service.getTransaction(id);
    }

}
