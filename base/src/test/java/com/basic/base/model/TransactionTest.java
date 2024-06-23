package com.basic.base.model;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.basic.base.enums.TransactionType;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {
    @InjectMocks
    private Transaction transaction;

    private final String id = "1";
    private final String accountNumber = "12345";
    private final double amount = 100.0;
    private final double balanceAfter = 200.0;
    private final Date createdAt = new Date();
    private final TransactionType transactionType = TransactionType.CREDIT;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        transaction.setId(id);
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setBalanceAfter(balanceAfter);
        transaction.setCreatedAt(createdAt);
        transaction.setTransactionType(transactionType);
    }

    @Test
    public void testGettersAndSetters() {
        assertThat(transaction.getId()).isEqualTo(id);
        assertThat(transaction.getAccountNumber()).isEqualTo(accountNumber);
        assertThat(transaction.getAmount()).isEqualTo(amount);
        assertThat(transaction.getBalanceAfter()).isEqualTo(balanceAfter);
        assertThat(transaction.getCreatedAt()).isEqualTo(createdAt);
        assertThat(transaction.getTransactionType()).isEqualTo(transactionType);
    }

    @Test
    public void testNoArgsConstructor() {
        Transaction newTransaction = new Transaction();
        assertThat(newTransaction).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        Transaction newTransaction = new Transaction();
        newTransaction.setId(id);
        newTransaction.setAccountNumber(accountNumber);
        newTransaction.setAmount(amount);
        newTransaction.setBalanceAfter(balanceAfter);
        newTransaction.setCreatedAt(createdAt);
        newTransaction.setTransactionType(transactionType);

        assertThat(newTransaction.getId()).isEqualTo(id);
        assertThat(newTransaction.getAccountNumber()).isEqualTo(accountNumber);
        assertThat(newTransaction.getAmount()).isEqualTo(amount);
        assertThat(newTransaction.getBalanceAfter()).isEqualTo(balanceAfter);
        assertThat(newTransaction.getCreatedAt()).isEqualTo(createdAt);
        assertThat(newTransaction.getTransactionType()).isEqualTo(transactionType);
    }

    @Test
    public void testToString() {
        String toString = transaction.toString();
        assertThat(toString).contains(id, accountNumber, String.valueOf(amount), String.valueOf(balanceAfter), createdAt.toString(), transactionType.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Transaction anotherTransaction = new Transaction();
        anotherTransaction.setId(id);
        anotherTransaction.setAccountNumber(accountNumber);
        anotherTransaction.setAmount(amount);
        anotherTransaction.setBalanceAfter(balanceAfter);
        anotherTransaction.setCreatedAt(createdAt);
        anotherTransaction.setTransactionType(transactionType);

        assertThat(transaction).isEqualTo(anotherTransaction);
        assertThat(transaction.hashCode()).isEqualTo(anotherTransaction.hashCode());
    }
}
