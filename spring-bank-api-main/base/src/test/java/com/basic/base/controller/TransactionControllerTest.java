package com.basic.base.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.basic.base.model.Account;
import com.basic.base.model.Transaction;
import com.basic.base.service.impl.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionServiceImpl transactionServiceImpl;

    @InjectMocks
    private TransactionController transactionController;

    private Transaction transaction;
    private Account account;

    @BeforeEach
    public void setUp() {
        transaction = new Transaction();
        transaction.setAccountNumber("123456");
        transaction.setAmount(100.0);

        account = new Account();
        account.setAccountNumber("123456");
    }

    @Test
    public void testDeposit_Success() throws Exception {
        when(transactionServiceImpl.deposit(any(String.class), any(Double.class))).thenReturn(transaction);

        ResponseEntity<?> response = transactionController.deposit(transaction);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(transaction, response.getBody());
        verify(transactionServiceImpl, times(1)).deposit(transaction.getAccountNumber(), transaction.getAmount());
    }

    @Test
    public void testDeposit_Failure() throws Exception {
        when(transactionServiceImpl.deposit(any(String.class), any(Double.class)))
                .thenThrow(new Exception("Deposit failed"));

        ResponseEntity<?> response = transactionController.deposit(transaction);

        assertEquals(500, response.getStatusCode().value());
        assertEquals("Deposit failed", ((HashMap<String, String>) response.getBody()).get("error"));
        verify(transactionServiceImpl, times(1)).deposit(transaction.getAccountNumber(), transaction.getAmount());
    }

    @Test
    public void testWithdraw_Success() throws Exception {
        when(transactionServiceImpl.withdraw(any(String.class), any(Double.class))).thenReturn(transaction);

        ResponseEntity<?> response = transactionController.withdraw(transaction);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(transaction, response.getBody());
        verify(transactionServiceImpl, times(1)).withdraw(transaction.getAccountNumber(), transaction.getAmount());
    }

    @Test
    public void testWithdraw_Failure() throws Exception {
        when(transactionServiceImpl.withdraw(any(String.class), any(Double.class)))
                .thenThrow(new Exception("Withdrawal failed"));

        ResponseEntity<?> response = transactionController.withdraw(transaction);

        assertEquals(500, response.getStatusCode().value());
        assertEquals("Withdrawal failed", ((HashMap<String, String>) response.getBody()).get("error"));
        verify(transactionServiceImpl, times(1)).withdraw(transaction.getAccountNumber(), transaction.getAmount());
    }

    @Test
    public void testGetRecentTransactions_Success() throws Exception {
        List<Transaction> transactions = Collections.singletonList(transaction);
        when(transactionServiceImpl.getRecentTransactions(any(String.class), anyInt())).thenReturn(transactions);

        ResponseEntity<?> response = transactionController.getRecentTransactions(account);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(transactions, response.getBody());
        verify(transactionServiceImpl, times(1)).getRecentTransactions(account.getAccountNumber(), 10);
    }

    @Test
    public void testGetRecentTransactions_Failure() throws Exception {
        when(transactionServiceImpl.getRecentTransactions(any(String.class), anyInt()))
                .thenThrow(new Exception("Transaction retrieval failed"));

        ResponseEntity<?> response = transactionController.getRecentTransactions(account);

        assertEquals(500, response.getStatusCode().value());
        assertEquals("Transaction retrieval failed", ((HashMap<String, String>) response.getBody()).get("error"));
        verify(transactionServiceImpl, times(1)).getRecentTransactions(account.getAccountNumber(), 10);
    }

}
