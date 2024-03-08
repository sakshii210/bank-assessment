package com.example.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.entity.Accounts;
import com.example.bank.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public boolean login(@RequestParam String customerId, @RequestParam String password) {
        return accountService.login(customerId, password);
    }

    @PostMapping("/register")
    public void register(@RequestBody Accounts account) {
        accountService.register(account);
    }

    @PutMapping("/changepwd/{customerId}/{oldPassword}/{newPassword}")
    public void changePassword(@PathVariable String customerId, @PathVariable String oldPassword,
                               @PathVariable String newPassword) {
        accountService.changePassword(customerId, oldPassword, newPassword);
    }

    @PostMapping("/transfer")
    public boolean transfer(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber,
                            @RequestParam double amount) {
        return accountService.transfer(fromAccountNumber, toAccountNumber, amount);
    }

    @GetMapping("/balance/{accountNumber}")
    public double getBalance(@PathVariable String accountNumber) {
        return accountService.getBalance(accountNumber);
    }

    @GetMapping("/accounts/below")
    public List<Accounts> getAccountsBelow(@RequestParam double amount) {
        return accountService.getAccountsBelow(amount);
    }

    @GetMapping("/accounts/above")
    public List<Accounts> getAccountsAbove(@RequestParam double amount) {
        return accountService.getAccountsAbove(amount);
    }
}
