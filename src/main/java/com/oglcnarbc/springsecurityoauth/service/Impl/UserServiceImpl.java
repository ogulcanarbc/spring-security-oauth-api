package com.oglcnarbc.springsecurityoauth.service.Impl;

import com.oglcnarbc.springsecurityoauth.dto.RegistrationRequest;
import com.oglcnarbc.springsecurityoauth.dto.UserDto;
import com.oglcnarbc.springsecurityoauth.entity.User;
import com.oglcnarbc.springsecurityoauth.repository.UserRepository;
import com.oglcnarbc.springsecurityoauth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto save(UserDto userDto) {
        if (userDto.getNameSurname() == null) {
            throw new IllegalArgumentException("username cannot to be null!");
        }
        User u = modelMapper.map(userDto, User.class);
        u = userRepository.save(u);
        userDto.setId(u.getId());

        return  userDto;
    }


    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {

            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setNameSurname(registrationRequest.getNameSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUsername(registrationRequest.getUsername());
            //username var mı yok mu gibi kontroller burda yapılmalı
            userRepository.save(user);
            return Boolean.TRUE;

        } catch (Exception e) {
            log.error("Registration ->", e);
            return Boolean.FALSE;
        }
    }
}
