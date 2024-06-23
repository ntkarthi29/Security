package com.basic.base.controller;

import com.basic.base.DTO.JwtAuthenticationResponse;
import com.basic.base.DTO.LoginRequest;
import com.basic.base.DTO.RefreshTokenRequest;
import com.basic.base.DTO.UserDTO;
import com.basic.base.model.Account;
import com.basic.base.model.User;
import com.basic.base.service.UserService;
import com.basic.base.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import static com.basic.base.enums.ERole.USER;

@RestController
@RequestMapping("/bank/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/test")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Public endpoint accessed successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody UserDTO user) {
        User registeredUser = userService.registerUser(user.getUserName(), user.getEmail(), user.getPassword(),user.getGender(),user.getPhoneNumber(),user.getAccountType(),user.getRoles());
        Account newAccount = accountService.createAccount(registeredUser.getUserName(), registeredUser.getEmail(), registeredUser.getPhoneNumber(), registeredUser.getGender(), registeredUser.getAccountType(),registeredUser.getRoles()); // Assuming a default savings account
        return ResponseEntity.ok(newAccount);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.LoginUser(loginRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(userService.refreshToken(refreshTokenRequest));
    }


}