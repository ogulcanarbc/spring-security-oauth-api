package com.oglcnarbc.springsecurityoauth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    // bu iki değişkenle dışarı token vericez.
    private String username;
    private String password;
}
