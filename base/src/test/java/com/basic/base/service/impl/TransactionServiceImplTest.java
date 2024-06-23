package com.basic.base.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.basic.base.enums.TransactionType;
import com.basic.base.model.Account;
import com.basic.base.model.Transaction;
import com.basic.base.repository.AccountRepository;
import com.basic.base.repository.TransactionRepository;
import com.basic.base.repository.impl.CustomAccountRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomAccountRepositoryImpl customAccountRepositoryImpl;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Account account;
    private Transaction transaction;
    private final String accountNumber = "12345";
    private final double amount = 100.0;
    private final double initialBalance = 500.0;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(initialBalance);

        transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setCreatedAt(new Date());
    }

    @Test
    public void testDeposit_Success() throws Exception {
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);
        transaction.setTransactionType(TransactionType.CREDIT);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.deposit(accountNumber, amount);

        assertNotNull(result);
        assertEquals(TransactionType.CREDIT, result.getTransactionType());
        assertEquals(accountNumber, result.getAccountNumber());
        assertEquals(amount, result.getAmount());

        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(customAccountRepositoryImpl, times(1)).updateBalanceByAccountNumber(accountNumber,
                initialBalance + amount);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testDeposit_AccountNotFound() {
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {
            transactionService.deposit(accountNumber, amount);
        });

        assertEquals("Account not found", exception.getMessage());

        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(customAccountRepositoryImpl, never()).updateBalanceByAccountNumber(anyString(), anyDouble());
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    public void testWithdraw_Success() throws Exception {
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);
        transaction.setTransactionType(TransactionType.DEBIT);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.withdraw(accountNumber, amount);

        assertNotNull(result);
        assertEquals(TransactionType.DEBIT, result.getTransactionType());
        assertEquals(accountNumber, result.getAccountNumber());
        assertEquals(amount, result.getAmount());

        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(customAccountRepositoryImpl, times(1)).updateBalanceByAccountNumber(accountNumber,
                initialBalance - amount);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testWithdraw_InsufficientBalance() {
        account.setBalance(amount - 50); // Set balance less than withdrawal amount
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);

        Exception exception = assertThrows(Exception.class, () -> {
            transactionService.withdraw(accountNumber, amount);
        });

        assertEquals("Insufficient balance", exception.getMessage());

        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(customAccountRepositoryImpl, never()).updateBalanceByAccountNumber(anyString(), anyDouble());
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    public void testWithdraw_AccountNotFound() {
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {
            transactionService.withdraw(accountNumber, amount);
        });

        assertEquals("Account not found", exception.getMessage());

        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(customAccountRepositoryImpl, never()).updateBalanceByAccountNumber(anyString(), anyDouble());
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    public void testGetRecentTransactions_Success() throws Exception {
        List<Transaction> transactions = Arrays.asList(transaction, transaction, transaction);
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);
        when(transactionRepository.findTopNByAccountNumberOrderByCreatedAtDesc(accountNumber, 3))
                .thenReturn(transactions);

        List<Transaction> result = transactionService.getRecentTransactions(accountNumber, 3);

        assertNotNull(result);
        assertEquals(3, result.size());

        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(transactionRepository, times(1)).findTopNByAccountNumberOrderByCreatedAtDesc(accountNumber, 3);
    }

    @Test
    public void testGetRecentTransactions_AccountNotFound() {
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {
            transactionService.getRecentTransactions(accountNumber, 3);
        });

        assertEquals("Account not found", exception.getMessage());

        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(transactionRepository, never()).findTopNByAccountNumberOrderByCreatedAtDesc(anyString(), anyInt());
    }
}
