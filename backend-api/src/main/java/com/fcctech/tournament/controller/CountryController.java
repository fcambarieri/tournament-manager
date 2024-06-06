package com.fcctech.tournament.controller;

import com.fcctech.tournament.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    public Set<Map<String, String>> findAll() {
        Set<Map<String, String>> result = new HashSet<>();
        countryRepository.findAll().forEach(country -> {
            result.add(Map.of("code", country.getCode(), "name", country.getName()));
        });
        return result;
    }
}
