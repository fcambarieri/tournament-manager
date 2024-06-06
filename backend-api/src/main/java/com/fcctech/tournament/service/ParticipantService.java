package com.fcctech.tournament.service;

import com.fcctech.tournament.entity.Participant;
import com.fcctech.tournament.entity.ParticipantTeam;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.payload.request.ParticipantRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface ParticipantService {

    public Participant registerParticipant(User registerBy, Tournament tournament, ParticipantTeam participantTeam, ParticipantRequest request);
    Participant registerParticipant(User registerBy, Long tournamentID, Long teamID, ParticipantRequest request);

    Optional<Participant> findById(Long participantId);

    void updateParticipant(Long userId, Long participantId, Long tournamentID, Long teamID, ParticipantRequest request);

    Set<Participant> findAllByTournamentIdAndTeamId(Long tournamentID, Long teamID);

    Set<Participant> findAllByTournamentId(Long tournamentID);

    void updateParticipantCheckIn(Long userId, Long participantId, Long tournamentID);

    void deleteParticipant(Long userId, Long participantId, Long tournamentID);

    Page<Participant> searchParticipants(Long tournamentID, int page, int size, String sortDir, String sort);
}
