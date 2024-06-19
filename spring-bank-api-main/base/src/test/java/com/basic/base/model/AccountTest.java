package com.basic.base.model;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {
    
    @InjectMocks
    private Account account;

    private final String id = "1";
    private final String userName = "JohnDoe";
    private final String email = "john.doe@example.com";
    private final String phoneNumber = "1234567890";
    private final Gender gender = Gender.MALE;
    private final AccountType accountType = AccountType.SAVINGS;
    private final String accountNumber = "12345";
    private final double balance = 1000.0;
    private final Date createdAt = new Date();
    private final Date updatedAt = new Date();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        account.setId(id);
        account.setUserName(userName);
        account.setEmail(email);
        account.setPhoneNumber(phoneNumber);
        account.setGender(gender);
        account.setAccountType(accountType);
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setCreatedAt(createdAt);
        account.setUpdatedAt(updatedAt);
    }

    @Test
    public void testGettersAndSetters() {
        assertThat(account.getId()).isEqualTo(id);
        assertThat(account.getUserName()).isEqualTo(userName);
        assertThat(account.getEmail()).isEqualTo(email);
        assertThat(account.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(account.getGender()).isEqualTo(gender);
        assertThat(account.getAccountType()).isEqualTo(accountType);
        assertThat(account.getAccountNumber()).isEqualTo(accountNumber);
        assertThat(account.getBalance()).isEqualTo(balance);
        assertThat(account.getCreatedAt()).isEqualTo(createdAt);
        assertThat(account.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    public void testNoArgsConstructor() {
        Account newAccount = new Account();
        assertThat(newAccount).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        Account newAccount = new Account();
        newAccount.setId(id);
        newAccount.setUserName(userName);
        newAccount.setEmail(email);
        newAccount.setPhoneNumber(phoneNumber);
        newAccount.setGender(gender);
        newAccount.setAccountType(accountType);
        newAccount.setAccountNumber(accountNumber);
        newAccount.setBalance(balance);
        newAccount.setCreatedAt(createdAt);
        newAccount.setUpdatedAt(updatedAt);

        assertThat(newAccount.getId()).isEqualTo(id);
        assertThat(newAccount.getUserName()).isEqualTo(userName);
        assertThat(newAccount.getEmail()).isEqualTo(email);
        assertThat(newAccount.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(newAccount.getGender()).isEqualTo(gender);
        assertThat(newAccount.getAccountType()).isEqualTo(accountType);
        assertThat(newAccount.getAccountNumber()).isEqualTo(accountNumber);
        assertThat(newAccount.getBalance()).isEqualTo(balance);
        assertThat(newAccount.getCreatedAt()).isEqualTo(createdAt);
        assertThat(newAccount.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    public void testToString() {
        String toString = account.toString();
        assertThat(toString).contains(id, userName, email, phoneNumber, gender.toString(), accountType.toString(), accountNumber, String.valueOf(balance), createdAt.toString(), updatedAt.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Account anotherAccount = new Account();
        anotherAccount.setId(id);
        anotherAccount.setUserName(userName);
        anotherAccount.setEmail(email);
        anotherAccount.setPhoneNumber(phoneNumber);
        anotherAccount.setGender(gender);
        anotherAccount.setAccountType(accountType);
        anotherAccount.setAccountNumber(accountNumber);
        anotherAccount.setBalance(balance);
        anotherAccount.setCreatedAt(createdAt);
        anotherAccount.setUpdatedAt(updatedAt);

        assertThat(account).isEqualTo(anotherAccount);
        assertThat(account.hashCode()).isEqualTo(anotherAccount.hashCode());
    }
}
