package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.Accounts;
import com.example.bank.repository.AccountRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepository;

    public boolean login(String customerId, String password) {
        Accounts account = accountRepository.findByCustomerId(customerId);
        return account != null && account.getPassword().equals(password);
    }

    public void register(Accounts account) {
        accountRepository.save(account);
    }

    public void changePassword(String customerId, String oldPassword, String newPassword) {
        Accounts account = accountRepository.findByCustomerId(customerId);
        if (account != null && account.getPassword().equals(oldPassword)) {
            account.setPassword(newPassword);
            accountRepository.save(account);
        }
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Accounts fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Accounts toAccount = accountRepository.findByAccountNumber(toAccountNumber);

        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
            return true;
        }
        return false;
    }

    public double getBalance(String accountNumber) {
        Accounts account = accountRepository.findByAccountNumber(accountNumber);
        return account != null ? account.getBalance() : 0;
    }

    public List<Accounts> getAccountsBelow(double amount) {
        return accountRepository.findAllByBalanceLessThan(amount);
    }

    public List<Accounts> getAccountsAbove(double amount) {
        return accountRepository.findAllByBalanceGreaterThan(amount);
    }
}
