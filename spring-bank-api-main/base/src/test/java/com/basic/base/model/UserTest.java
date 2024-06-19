package com.basic.base.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @InjectMocks
    private User user;

    private final String id = "1";
    private final String userName = "testuser";
    private final String email = "testuser@example.com";
    private final String password = "password123";
    private final Gender gender = Gender.MALE;
    private final String phoneNumber = "1234567890";
    private final AccountType accountType = AccountType.SAVINGS;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user.setId(id);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setAccountType(accountType);
    }

    @Test
    public void testGettersAndSetters() {
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUserName()).isEqualTo(userName);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getGender()).isEqualTo(gender);
        assertThat(user.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(user.getAccountType()).isEqualTo(accountType);
    }

    @Test
    public void testNoArgsConstructor() {
        User newUser = new User();
        assertThat(newUser).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        User newUser = new User();
        newUser.setId(id);
        newUser.setUserName(userName);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setGender(gender);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setAccountType(accountType);

        assertThat(newUser.getId()).isEqualTo(id);
        assertThat(newUser.getUserName()).isEqualTo(userName);
        assertThat(newUser.getEmail()).isEqualTo(email);
        assertThat(newUser.getPassword()).isEqualTo(password);
        assertThat(newUser.getGender()).isEqualTo(gender);
        assertThat(newUser.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(newUser.getAccountType()).isEqualTo(accountType);
    }

    @Test
    public void testToString() {
        String toString = user.toString();
        assertThat(toString).contains(id, userName, email, password, gender.toString(), phoneNumber,
                accountType.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        User anotherUser = new User();
        anotherUser.setId(id);
        anotherUser.setUserName(userName);
        anotherUser.setEmail(email);
        anotherUser.setPassword(password);
        anotherUser.setGender(gender);
        anotherUser.setPhoneNumber(phoneNumber);
        anotherUser.setAccountType(accountType);

        assertThat(user).isEqualTo(anotherUser);
        assertThat(user.hashCode()).isEqualTo(anotherUser.hashCode());
    }
}
