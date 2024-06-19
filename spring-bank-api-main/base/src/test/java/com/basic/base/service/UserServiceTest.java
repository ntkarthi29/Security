package com.basic.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;
import com.basic.base.model.User;
import com.basic.base.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private final String userName = "testUser";
    private final String email = "testUser@example.com";
    private final String password = "password";
    private final Gender gender = Gender.MALE;
    private final String phoneNumber = "1234567890";
    private final AccountType accountType = AccountType.SAVINGS;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setAccountType(accountType);
    }

    @Test
    public void testRegisterUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.registerUser(userName, email, password, gender, phoneNumber, accountType);

        assertEquals(userName, registeredUser.getUserName());
        assertEquals(email, registeredUser.getEmail());
        assertEquals(password, registeredUser.getPassword());
        assertEquals(gender, registeredUser.getGender());
        assertEquals(phoneNumber, registeredUser.getPhoneNumber());
        assertEquals(accountType, registeredUser.getAccountType());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testLoginUser_Success() {
        when(userRepository.findByUserName(userName)).thenReturn(user);

        User loggedInUser = userService.LoginUser(userName, password);

        assertNotNull(loggedInUser);
        assertEquals(userName, loggedInUser.getUserName());
        assertEquals(password, loggedInUser.getPassword());

        verify(userRepository, times(1)).findByUserName(userName);
    }

    @Test
    public void testLoginUser_Failure() {
        when(userRepository.findByUserName(userName)).thenReturn(user);

        User loggedInUser = userService.LoginUser(userName, "wrongPassword");

        assertNull(loggedInUser);

        verify(userRepository, times(1)).findByUserName(userName);
    }

    @Test
    public void testLoginUser_UserNotFound() {
        when(userRepository.findByUserName(userName)).thenReturn(null);

        User loggedInUser = userService.LoginUser(userName, password);

        assertNull(loggedInUser);

        verify(userRepository, times(1)).findByUserName(userName);
    }
}
