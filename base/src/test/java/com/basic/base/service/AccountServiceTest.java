package com.basic.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;
import com.basic.base.model.Account;
import com.basic.base.repository.AccountRepository;
import com.basic.base.repository.impl.CustomAccountRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomAccountRepositoryImpl customAccountRepositoryImpl;

    @InjectMocks
    private AccountService accountService;

    private Account account;
    private final String userName = "testUser";
    private final String email = "testUser@example.com";
    private final String phoneNumber = "1234567890";
    private final Gender gender = Gender.MALE;
    private final AccountType accountType = AccountType.SAVINGS;
    private final String accountNumber = "1001";

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setUserName(userName);
        account.setEmail(email);
        account.setPhoneNumber(phoneNumber);
        account.setAccountNumber(accountNumber);
        account.setGender(gender);
        account.setAccountType(accountType);
        account.setCreatedAt(new Date(System.currentTimeMillis()));
        account.setUpdatedAt(new Date(System.currentTimeMillis()));
    }

    @Test
    public void testCreateAccount() {
        when(customAccountRepositoryImpl.findLastAccountNumber()).thenReturn(1000L);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount(userName, email, phoneNumber, gender, accountType, registeredUser.getRoles());

        assertEquals(userName, createdAccount.getUserName());
        assertEquals(email, createdAccount.getEmail());
        assertEquals(phoneNumber, createdAccount.getPhoneNumber());
        assertEquals(accountNumber, createdAccount.getAccountNumber());
        assertEquals(gender, createdAccount.getGender());
        assertEquals(accountType, createdAccount.getAccountType());

        verify(customAccountRepositoryImpl, times(1)).findLastAccountNumber();
        verify(accountRepository, times(1)).save(any(Account.class));
    }
}
