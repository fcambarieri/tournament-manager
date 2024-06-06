package com.fcctech.tournament.service;

import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.payload.request.ParticipantTeamRequest;
import com.fcctech.tournament.payload.request.TournamentRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TournamentService {
    Tournament createTournament(TournamentRequest request, User user);

    Optional<Tournament> findById(Long id);

    void updateTournament(Tournament tournament, TournamentRequest request);

    Page<Tournament> listOwnerTournament(Long ownerId, int page, int size, String sortDir, String sort);

    void subscribeMe(Long userId, Long tournamentId);

    void subscribeTeam(Long userId, Long tournamentId, ParticipantTeamRequest request);

}
