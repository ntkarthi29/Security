package com.basic.base.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountDTOTest {
    @InjectMocks
    private AccountDTO accountDTO;

    private final String userName = "testuser";
    private final String email = "testuser@example.com";
    private final String phoneNumber = "1234567890";
    private final Gender gender = Gender.MALE;
    private final AccountType accountType = AccountType.SAVINGS;
    private final String accountNumber = "12345";
    private final double balance = 1000.0;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        accountDTO.setUserName(userName);
        accountDTO.setEmail(email);
        accountDTO.setPhoneNumber(phoneNumber);
        accountDTO.setGender(gender);
        accountDTO.setAccountType(accountType);
        accountDTO.setAccountNumber(accountNumber);
        accountDTO.setBalance(balance);
    }

    @Test
    public void testGettersAndSetters() {
        assertThat(accountDTO.getUserName()).isEqualTo(userName);
        assertThat(accountDTO.getEmail()).isEqualTo(email);
        assertThat(accountDTO.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(accountDTO.getGender()).isEqualTo(gender);
        assertThat(accountDTO.getAccountType()).isEqualTo(accountType);
        assertThat(accountDTO.getAccountNumber()).isEqualTo(accountNumber);
        assertThat(accountDTO.getBalance()).isEqualTo(balance);
    }

    @Test
    public void testNoArgsConstructor() {
        AccountDTO newAccountDTO = new AccountDTO();
        assertThat(newAccountDTO).isNotNull();
    }

    @Test
    public void testToString() {
        String toString = accountDTO.toString();
        assertThat(toString).contains(userName, email, phoneNumber, gender.toString(), accountType.toString(), accountNumber, String.valueOf(balance));
    }

}
