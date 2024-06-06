package com.fcctech.tournament.controller;


import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.payload.request.LoginRequest;
import com.fcctech.tournament.payload.request.RegistrationRequest;
import com.fcctech.tournament.payload.response.LoginResponse;
import com.fcctech.tournament.payload.request.PasswordRequest;
import com.fcctech.tournament.payload.response.RegistrationResponse;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.AuthService;
import com.fcctech.tournament.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmailOrUserName(), request.getPassword());
    }


    @PutMapping("/password")
    public void updateMe(@AuthenticationPrincipal UserPrincipal principal, @RequestBody PasswordRequest passwordRequest) {
        userService.resetPassword(principal.getUserId(), passwordRequest.getPassword());
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RegistrationResponse registerUser(@RequestBody RegistrationRequest request) {
        User user = userService.createUser(request);
        return RegistrationResponse.builder().userId(user.getId()).build();
    }
}
