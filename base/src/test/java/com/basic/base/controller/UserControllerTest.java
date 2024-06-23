package com.basic.base.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.basic.base.enums.ERole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.basic.base.DTO.UserDTO;
import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;
import com.basic.base.model.Account;
import com.basic.base.model.User;
import com.basic.base.service.AccountService;
import com.basic.base.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;
    private User user;
    private Account account;

    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO();
        userDTO.setUserName("testUser");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");
        userDTO.setGender(Gender.MALE);
        userDTO.setPhoneNumber("1234567890");
        userDTO.setAccountType(AccountType.SAVINGS);

        user = new User();
        user.setUserName("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setGender(Gender.MALE);
        user.setPhoneNumber("1234567890");
        user.setAccountType(AccountType.SAVINGS);
        user.setRole(ERole.USER);

        account = new Account();
        account.setUserName("testUser");
        account.setEmail("test@example.com");
        account.setPhoneNumber("1234567890");
        account.setGender(Gender.MALE);
        account.setAccountType(AccountType.SAVINGS);
    }

    @Test
    public void testRegister_Success() {
        when(userService.registerUser(anyString(), anyString(), anyString(), any(Gender.class), anyString(),
                any(AccountType.class), any(ERole.class))).thenReturn(user);
        when(accountService.createAccount(anyString(), anyString(), anyString(), any(Gender.class),
                any(AccountType.class), registeredUser.getRoles())).thenReturn(account);

        ResponseEntity<Account> response = userController.register(userDTO);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(account, response.getBody());
        verify(userService, times(1)).registerUser(userDTO.getUserName(), userDTO.getEmail(), userDTO.getPassword(),
                userDTO.getGender(), userDTO.getPhoneNumber(), userDTO.getAccountType(),userDTO.getRoles());
        verify(accountService, times(1)).createAccount(user.getUserName(), user.getEmail(), user.getPhoneNumber(),
                user.getGender(), user.getAccountType(), registeredUser.getRoles());
    }

    @Test
    public void testLogin_Success() {
        when(userService.LoginUser(anyString(), anyString())).thenReturn(user);

        ResponseEntity<User> response = userController.login(user);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).LoginUser(user.getUserName(), user.getPassword());
    }

    @Test
    public void testLogin_Failure() {
        when(userService.LoginUser(anyString(), anyString())).thenReturn(null);

        ResponseEntity<User> response = userController.login(user);

        assertEquals(401, response.getStatusCode().value());
        verify(userService, times(1)).LoginUser(user.getUserName(), user.getPassword());
    }

}
