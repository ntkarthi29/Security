package com.basic.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.base.enums.TransactionType;
import com.basic.base.model.Account;
import com.basic.base.model.Transaction;
import com.basic.base.repository.AccountRepository;
import com.basic.base.repository.TransactionRepository;
import com.basic.base.repository.impl.CustomAccountRepositoryImpl;
import com.basic.base.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomAccountRepositoryImpl customAccountRepositoryImpl;

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction deposit(String accountNumber, double amount) throws Exception {
        Account existingAccount = accountRepository.findByAccountNumber(accountNumber);
        if (existingAccount != null) {
            double accountBalance = existingAccount.getBalance();
            double newBalance = accountBalance + amount;
            Transaction transaction = new Transaction();
            transaction.setAccountNumber(accountNumber);
            transaction.setAmount(amount);
            transaction.setTransactionType(TransactionType.CREDIT);
            transaction.setCreatedAt(new Date(System.currentTimeMillis()));
            transaction.setBalanceAfter(newBalance);
            customAccountRepositoryImpl.updateBalanceByAccountNumber(existingAccount.getAccountNumber(), newBalance);
            return transactionRepository.save(transaction);
        } else {
            throw new Exception("Account not found");
        }
    }

    public Transaction withdraw(String accountNumber, double amount) throws Exception {
        Account existingAccount = accountRepository.findByAccountNumber(accountNumber);
        if (existingAccount != null) {
            double accountBalance = existingAccount.getBalance();
            if (accountBalance < amount) {
                throw new Exception("Insufficient balance");
            }
            double newBalance = accountBalance - amount;
            Transaction transaction = new Transaction();
            transaction.setAccountNumber(accountNumber);
            transaction.setAmount(amount);
            transaction.setTransactionType(TransactionType.DEBIT);
            transaction.setCreatedAt(new Date(System.currentTimeMillis()));
            transaction.setBalanceAfter(newBalance);
            customAccountRepositoryImpl.updateBalanceByAccountNumber(existingAccount.getAccountNumber(), newBalance);
            return transactionRepository.save(transaction);
        } else {
            throw new Exception("Account not found");
        }
    }

    @Override
    public List<Transaction> getRecentTransactions(String accountNumber, int numberOfTransactions) throws Exception {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            return transactionRepository
                    .findTopNByAccountNumberOrderByCreatedAtDesc(accountNumber, numberOfTransactions).stream()
                    .limit(numberOfTransactions).toList();
        } else {
            throw new Exception("Account not found");
        }
    }
}
