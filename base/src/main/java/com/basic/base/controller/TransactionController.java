package com.basic.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.base.model.Account;
import com.basic.base.model.Transaction;
import com.basic.base.service.impl.TransactionServiceImpl;


@RestController
@RequestMapping("/bank/transaction")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Transaction transaction) {
        try {
            Transaction newTransaction = transactionServiceImpl.deposit(transaction.getAccountNumber(),
                    transaction.getAmount());
            return ResponseEntity.ok(newTransaction);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Transaction transaction) {
        try {
            Transaction newTransaction = transactionServiceImpl.withdraw(transaction.getAccountNumber(),
                    transaction.getAmount());
            return ResponseEntity.ok(newTransaction);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentTransactions(@RequestBody Account account) {
        try {
            List<Transaction> recentTransactions = transactionServiceImpl.getRecentTransactions(account.getAccountNumber(), 2);
            return ResponseEntity.ok(recentTransactions);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

}
