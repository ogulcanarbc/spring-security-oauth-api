package com.oglcnarbc.springsecurityoauth.service;

import com.oglcnarbc.springsecurityoauth.dto.UserDto;
import com.oglcnarbc.springsecurityoauth.entity.User;

public interface UserService {

    UserDto save(UserDto user);

    User getByUsername(String username);

}
