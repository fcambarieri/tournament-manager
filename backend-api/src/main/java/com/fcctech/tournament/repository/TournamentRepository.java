package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.ParticipantTeam;
import com.fcctech.tournament.entity.Tournament;

import com.fcctech.tournament.entity.TournamentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {

    @Query("select t from Tournament t where t.owner.id =:ownerId")
    Page<Tournament> findTournamentByOwner(@Param("ownerId") Long ownerId, Pageable pageable);


    @Query("select pt from ParticipantTeam pt where pt.tournament.id = :tournamentId")
    Optional<ParticipantTeam> findParticipantTeamByTournamentId(@Param("tournamentId") Long tournamentId);

    @Query("select t from TournamentType t where t.name = :name")
    Optional<TournamentType> findType(@Param("name") String name);
}
