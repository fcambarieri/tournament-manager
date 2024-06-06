package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {

}
