package com.oglcnarbc.springsecurityoauth.api;

import com.oglcnarbc.springsecurityoauth.dto.LoginRequest;
import com.oglcnarbc.springsecurityoauth.dto.RegistrationRequest;
import com.oglcnarbc.springsecurityoauth.dto.TokenResponse;
import com.oglcnarbc.springsecurityoauth.entity.User;
import com.oglcnarbc.springsecurityoauth.repository.UserRepository;
import com.oglcnarbc.springsecurityoauth.security.JwtTokenUtil;
import com.oglcnarbc.springsecurityoauth.service.Impl.UserServiceImpl;
import com.oglcnarbc.springsecurityoauth.util.ApiPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600) // maksmimum 3600 ms'de requeste cevap verir
@RestController
@RequestMapping(ApiPath.AccountCtrl.CTRL)
public class Account {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepository.findByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Boolean> register(@RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {
        Boolean response = userService.register(registrationRequest);
        return ResponseEntity.ok(response);
    }
}
