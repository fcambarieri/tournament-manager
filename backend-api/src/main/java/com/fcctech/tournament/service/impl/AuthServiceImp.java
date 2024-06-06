package com.fcctech.tournament.service.impl;

import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.exception.BusinessException;
import com.fcctech.tournament.payload.response.LoginResponse;
import com.fcctech.tournament.repository.UserRepository;
import com.fcctech.tournament.security.JwtIssuer;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    private final String ERROR = "User or password are wronng";
    @Override
    public LoginResponse attemptLogin(String emailOrUserName, String password) {

        Optional<User> user = userRepository.findByEmailOrUserName(emailOrUserName);

        if (user.isEmpty()) {
          throw new BusinessException(ERROR, HttpStatus.BAD_REQUEST);
        }

        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new BusinessException(ERROR, HttpStatus.BAD_REQUEST);
        }

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(emailOrUserName, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var token = jwtIssuer.issue(JwtIssuer.Request.builder()
                .userId(principal.getUserId())
                .email(principal.getEmail())
                .roles(principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build());

        return LoginResponse.builder()
                .token(token)
                .build();
    }

}
