package com.oglcnarbc.springsecurityoauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//son kullanıcıya (client) bu token bilgileri dönecek
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    private String username;
    private String token;
}
