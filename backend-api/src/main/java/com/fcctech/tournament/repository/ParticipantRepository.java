package com.fcctech.tournament.repository;

import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.entity.BaseCategory;
import com.fcctech.tournament.entity.Competition;
import com.fcctech.tournament.entity.Participant;
import com.fcctech.tournament.entity.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {

    @Query("select case when count(p)> 0 then true else false end from Participant p inner join Modality m " +
            " on p.id = m.participant.id " +
            " where p.tournament.id = :tournamentId" +
            " and m.category.id = :categoryId")
    boolean existsParticipantInCategory(@Param("tournamentId") Long tournamentId, @Param("categoryId") Long categoryId);


    @Query("select p from Participant p where p.tournament.id = :tournamentId and p.team.id = :teamId")
    Set<Participant> findAllParticipantByTeamAndTournamentId(@Param("tournamentId") Long tournamentId, @Param("teamId") Long teamId);

    @Query("select p from Participant p where p.tournament.id = :tournamentId")
    Set<Participant> findAllParticipantByTournamentId(@Param("tournamentId") Long tournamentId);


    @Query("select p from Participant p where p.tournament.id = :tournamentId")
    Page<Participant> findAllParticipantByTournamentId(@Param("tournamentId") Long tournamentId, Pageable pageable);

    @Query("select p from Competition p where p.tournament.id = :tournamentId")
    Page<Competition> findCompetitiontByTournamentId(@Param("tournamentId") Long tournamentId, Pageable pageable);
}
