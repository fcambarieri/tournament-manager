package com.fcctech.tournament.repository;

import com.fcctech.tournament.entity.ParticipantTeam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface ParticipantTeamRepository extends CrudRepository<ParticipantTeam, Long> {

    @Query("select pt from ParticipantTeam pt where pt.tournament.id = :tournamentId and " +
            "pt.subscriber.id = :userId")
    Optional<ParticipantTeam> findBySubscriberAndTournamentId(@Param("userId") Long userId, @Param("tournamentId") Long tournamentId);



    @Query("select pt from ParticipantTeam pt where pt.tournament.id = :tournamentId and " +
            "pt.email = :email")
    Optional<ParticipantTeam> findByTournamentIdAndEmail(@Param("tournamentId") Long tournamentId, @Param("email") String email);
}
