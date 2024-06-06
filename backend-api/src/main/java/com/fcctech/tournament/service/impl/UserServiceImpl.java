package com.fcctech.tournament.service.impl;

import com.fcctech.tournament.entity.Account;
import com.fcctech.tournament.entity.Role;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.BusinessException;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.payload.request.AccountRequest;
import com.fcctech.tournament.payload.request.RegistrationRequest;
import com.fcctech.tournament.repository.AccountRepository;
import com.fcctech.tournament.repository.CountryRepository;
import com.fcctech.tournament.repository.RoleRepository;
import com.fcctech.tournament.repository.UserRepository;
import com.fcctech.tournament.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return  userRepository.findByEmailOrUserName(email);

    }

    @Override
    public User createUser(RegistrationRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new BusinessException("Username is already exists", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in database
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email is already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDateCreated(new Date());
        user.setRoles(roles);

       return userRepository.save(user);
    }

    @Override
    public void resetPassword(Long userId, String newPassword) {
        String encodePassword = passwordEncoder.encode(newPassword);
        User user = userRepository.findById(userId).orElseThrow();
        user.setPassword(encodePassword);
        userRepository.save(user);
    }

    @Override
    public Account createAccount(Long userId, AccountRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));

        if (accountRepository.existUserAccount(userId)) {
            throw new BusinessException("account already created", HttpStatus.CONFLICT);
        }

        validateCountryCode(request.getCountry());

        Account account = new Account();
        account.setOwner(user);
        account.setCountryCode(request.getCountry());
        account.setPhoneNumber(request.getPhoneNumber());
        account.setAssociationName(request.getAssociationName());
        account.setCityName(request.getCityName());
        account.setStateName(request.getStateName());
        account.setStreetName(request.getStreetName());
        account.setStreetNumber(request.getStreetNumber());
        return accountRepository.save(account);
    }

    @Override
    public void updateAccount(Long userId, Long accountId, AccountRequest request) {
        Account account = accountRepository.findById(accountId).orElseThrow((() -> new NotFoundException("account not found")));
        if (!account.getOwner().getId().equals(userId)) {
            throw new BusinessException("user access denied", HttpStatus.FORBIDDEN);
        }

        validateCountryCode(request.getCountry());

        account.setCountryCode(request.getCountry());
        account.setPhoneNumber(request.getPhoneNumber());
        account.setAssociationName(request.getAssociationName());
        account.setCityName(request.getCityName());
        account.setStateName(request.getStateName());
        account.setStreetName(request.getStreetName());
        account.setStreetNumber(request.getStreetNumber());
        accountRepository.save(account);
    }

    @Override
    public Account findAccountById(Long userId, Long accountId) {
        return accountRepository.findById(accountId).orElseThrow((() -> new NotFoundException("account not found")));
    }

    private void validateCountryCode(String code) {
        if (!countryRepository.existsById(code)) {
            throw new BadRequestException(String.format("country %s not found", code));
        }
    }
}
