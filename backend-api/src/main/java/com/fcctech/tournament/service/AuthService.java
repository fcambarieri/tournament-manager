package com.fcctech.tournament.service;

import com.fcctech.tournament.payload.response.LoginResponse;

public interface AuthService {

    LoginResponse attemptLogin(String emailOrUserName, String password);
}

