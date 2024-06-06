package com.fcctech.tournament.controller;

import com.fcctech.tournament.entity.Participant;
import com.fcctech.tournament.entity.ParticipantTeam;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.payload.request.ParticipantRequest;
import com.fcctech.tournament.payload.request.ParticipantTeamRequest;
import com.fcctech.tournament.payload.response.ParticipantResponse;
import com.fcctech.tournament.repository.ParticipantTeamRepository;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.ParticipantService;
import com.fcctech.tournament.service.TournamentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class TournamentSubscriptionController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ParticipantTeamRepository participantTeamRepository;

    /**
     * Used by user that wants to subscribe
     * */
    @PostMapping("/tournaments/{tournamentId}/subscribe_me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void subscription(@AuthenticationPrincipal UserPrincipal principal, @PathVariable("tournamentId") Long tournamentId) {
        tournamentService.subscribeMe(principal.getUserId(), tournamentId);
    }

    /**
     * Used by tournament Owner
     * */
    @PostMapping("/tournaments/{tournamentId}/team_subscription")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void subscribeATeam(@AuthenticationPrincipal UserPrincipal principal,
                               @PathVariable("tournamentId") Long tournamentId,
                               @RequestBody ParticipantTeamRequest request) {
        tournamentService.subscribeTeam(principal.getUserId(), tournamentId, request);
    }

    @PostMapping("/tournaments/{tournamentId}/participants")
    public ResponseEntity createParticipant(@AuthenticationPrincipal UserPrincipal principal,
                                            @PathVariable Long tournamentId,
                                            @RequestBody ParticipantRequest request) {

        ParticipantTeam team = participantTeamRepository.findBySubscriberAndTournamentId(principal.getUserId(),tournamentId).orElseThrow(() -> new NotFoundException("team not found"));

        Participant participant = participantService.registerParticipant(team.getSubscriber(), team.getTournament(), team, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ParticipantResponse.builder().participantId(participant.getId()).build())
                ;
    }
}
