package com.example.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bank.entity.Accounts;

public interface AccountRepo extends JpaRepository<Accounts, Long> {

    Accounts findByCustomerId(String customerId);

    Accounts findByAccountNumber(String accountNumber);
    
    @Query("SELECT a FROM Accounts a WHERE a.balance < :balance")
    List<Accounts> findAllByBalanceLessThan(@Param("balance") double balance);

    @Query("SELECT a FROM Accounts a WHERE a.balance > :balance")
    List<Accounts> findAllByBalanceGreaterThan(@Param("balance") double balance);
}

