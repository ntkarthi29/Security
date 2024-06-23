package com.basic.base.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;

    public String getUserName() {
        return username;
    }
}
