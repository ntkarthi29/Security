package com.basic.base.service;

import com.basic.base.DTO.JwtAuthenticationResponse;
import com.basic.base.DTO.LoginRequest;
import com.basic.base.DTO.RefreshTokenRequest;
import com.basic.base.enums.ERole;
import com.basic.base.model.User;
import com.basic.base.repository.UserRepository;
import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository UserRepository;
    private PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private AuthenticationManager authenticationManager;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username){
                return UserRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
            }

        };
    }

    public User registerUser(String userName, String email, String password, Gender gender, String phoneNumber, AccountType accType, ERole roles) {
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setAccountType(accType);
        user.setRole(roles);
        return UserRepository.save(user);
    }

//    public User LoginUser(String userName,String password) {
//        User user = UserRepository.findByUserName(userName);
//        if (user != null && password.equals(user.getPassword())) {
//            return user;
//        }
//        return null;
//    }
    public JwtAuthenticationResponse LoginUser(LoginRequest loginRequest) {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        var user = UserRepository.findByUserName(loginRequest.getUsername());
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
}
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = UserRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    }
