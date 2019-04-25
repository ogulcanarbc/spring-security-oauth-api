package com.oglcnarbc.springsecurityoauth.repository;

import com.oglcnarbc.springsecurityoauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
