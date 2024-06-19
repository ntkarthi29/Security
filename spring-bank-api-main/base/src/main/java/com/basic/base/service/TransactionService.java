package com.basic.base.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.basic.base.model.Transaction;

@Service
public interface TransactionService {
    public Transaction deposit(String accountNumber, double amount) throws Exception;

    public Transaction withdraw(String accountNumber, double amount) throws Exception;

    public List<Transaction> getRecentTransactions(String accountNumber, int numberOfTransactions) throws Exception;
}
