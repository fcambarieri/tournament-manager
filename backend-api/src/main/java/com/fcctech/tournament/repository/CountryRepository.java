package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String> {

}
