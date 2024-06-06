package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.Competition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

    @Query("select c from Competition c where c.tournament.id = :tournamentId and c.id = :id")
    Optional<Competition> findByTournamentIdAndId(@Param("tournamentId") Long tournamentId, @Param("id") Long id);
}
