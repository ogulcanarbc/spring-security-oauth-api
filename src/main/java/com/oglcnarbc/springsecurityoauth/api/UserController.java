package com.oglcnarbc.springsecurityoauth.api;


import com.oglcnarbc.springsecurityoauth.dto.UserDto;
import com.oglcnarbc.springsecurityoauth.service.Impl.UserServiceImpl;
import com.oglcnarbc.springsecurityoauth.util.ApiPath;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping(ApiPath.UserCRTL.CTRL)
public class UserController {

    private final UserServiceImpl userServiceImpl;

    UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl =userServiceImpl;
    }

    @PostMapping
    @ApiOperation(value = "Create user", response = UserDto.class)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(userServiceImpl.save(user));
    }
}
