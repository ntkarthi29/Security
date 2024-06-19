package com.basic.base.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;

@Data
public class AccountDTO {

    @NotNull(message = "Username cannot be null")
    private String userName;

    @Email(message = "Invalid email address")
    private String email;

    @NotNull(message = "Phone Number cannot be null")
    private String phoneNumber;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @NotNull(message = "Account type cannot be null")
    private AccountType accountType;
    
    private String accountNumber;
    private double balance;
}