package com.basic.base.DTO;

import com.basic.base.enums.ERole;
import com.basic.base.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import com.basic.base.enums.AccountType;

@Data
public class UserDTO {

    @NotNull(message = "Username cannot be null")
    private String userName;
    @Email(message = "Invalid email address")
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Gender cannot be null")
    private Gender gender;
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;
    @NotNull(message = "Account type cannot be null")
    private AccountType accountType;
    private ERole roles;
}
