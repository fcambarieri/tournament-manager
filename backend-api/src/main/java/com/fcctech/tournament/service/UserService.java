package com.fcctech.tournament.service;

import com.fcctech.tournament.entity.Account;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.payload.request.AccountRequest;
import com.fcctech.tournament.payload.request.RegistrationRequest;

import java.util.Optional;

public interface UserService  {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User createUser(RegistrationRequest request);

    void resetPassword(Long userId, String newPassword);

    Account createAccount(Long userId, AccountRequest request);

    void updateAccount(Long userId, Long accountId, AccountRequest request);

    Account findAccountById(Long userId, Long accountId);
}
