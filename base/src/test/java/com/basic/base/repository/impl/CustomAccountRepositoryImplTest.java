package com.basic.base.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.basic.base.model.Account;

@ExtendWith(MockitoExtension.class)
public class CustomAccountRepositoryImplTest {
    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private CustomAccountRepositoryImpl customAccountRepositoryImpl;

    private Account account;

    private final String accountNumber = "1234567890";
    private final double newBalance = 5000.0;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(newBalance);
    }

    @Test
    public void testUpdateBalanceByAccountNumber() {
        when(mongoTemplate.findAndModify(any(Query.class), any(Update.class), eq(Account.class))).thenReturn(account);

        Account result = customAccountRepositoryImpl.updateBalanceByAccountNumber(accountNumber, newBalance);

        assertNotNull(result);
        assertEquals(accountNumber, result.getAccountNumber());
        assertEquals(newBalance, result.getBalance());

        verify(mongoTemplate, times(1)).findAndModify(any(Query.class), any(Update.class), eq(Account.class));
    }

    @Test
    public void testFindLastAccountNumber() {
        when(mongoTemplate.findOne(any(Query.class), eq(Account.class))).thenReturn(account);

        long result = customAccountRepositoryImpl.findLastAccountNumber();

        assertEquals(Long.parseLong(accountNumber), result);

        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Account.class));
    }

    @Test
    public void testFindLastAccountNumber_NoAccount() {
        when(mongoTemplate.findOne(any(Query.class), eq(Account.class))).thenReturn(null);

        long result = customAccountRepositoryImpl.findLastAccountNumber();

        assertEquals(0, result);

        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Account.class));
    }
}
