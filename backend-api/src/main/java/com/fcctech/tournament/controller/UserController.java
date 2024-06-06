package com.fcctech.tournament.controller;

import com.fcctech.tournament.entity.Account;
import com.fcctech.tournament.entity.Role;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.payload.request.AccountRequest;
import com.fcctech.tournament.payload.request.PasswordRequest;
import com.fcctech.tournament.payload.request.RegistrationRequest;
import com.fcctech.tournament.payload.response.EntityIdResponse;
import com.fcctech.tournament.payload.response.RegistrationResponse;
import com.fcctech.tournament.payload.response.UserAccountResponse;
import com.fcctech.tournament.payload.response.UserResponse;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/me")
    public ResponseEntity<UserResponse> getMe(@AuthenticationPrincipal UserPrincipal principal) {
        User user = userService.findById(principal.getUserId()).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(
                UserResponse
                        .builder()
                        .userName(user.getUserName())
                        .id(user.getId())
                        .email(user.getEmail())
                        .dateCreated(user.getDateCreated())
                        .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                        .build()
        );
    }

    //account
    @PostMapping("/user/account")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityIdResponse createAccount(@AuthenticationPrincipal UserPrincipal principal, @RequestBody AccountRequest request) {
        Account account = userService.createAccount(principal.getUserId(), request);
        return EntityIdResponse.builder()
                .id(account.getId())
                .build();
    }

    @PutMapping("/user/account/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createAccount(@AuthenticationPrincipal UserPrincipal principal,
                              @PathVariable("accountId") Long accountId,
                              @RequestBody AccountRequest request) {
        userService.updateAccount(principal.getUserId(), accountId, request);
    }

    @GetMapping("/user/account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public UserAccountResponse createAccount(@AuthenticationPrincipal UserPrincipal principal,
                              @PathVariable("accountId") Long accountId) {
        Account account = userService.findAccountById(principal.getUserId(), accountId);
        return UserAccountResponse.builder()
                .email(account.getOwner().getEmail())
                .associationName(account.getAssociationName())
                .phoneNumber(account.getPhoneNumber())
                .streetName(account.getStreetName())
                .streetNumber(account.getStreetNumber())
                .cityName(account.getCityName())
                .stateName(account.getStateName())
                .country(account.getCountryCode())
                .build();
    }


}
