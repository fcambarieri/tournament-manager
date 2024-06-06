package com.fcctech.tournament.controller;

import com.fcctech.tournament.entity.Participant;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.payload.request.ParticipantRequest;
import com.fcctech.tournament.payload.response.*;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.ParticipantService;
import com.fcctech.tournament.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/tournaments")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private UserService userService;

    @PostMapping("/{tournamentID}/teams/{teamID}/participants")
    public ResponseEntity createParticipant(@AuthenticationPrincipal UserPrincipal principal,
                                            @PathVariable Long tournamentID, @PathVariable Long teamID,
                                            @RequestBody ParticipantRequest request) {

        User registerBy = userService.findById(principal.getUserId()).get();

        Participant participant = participantService.registerParticipant(registerBy, tournamentID, teamID, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ParticipantResponse.builder().participantId(participant.getId()).build())
                ;
    }

    @GetMapping("/{tournamentID}/teams/{teamID}/participants/{participantId}")
    public ResponseEntity<ParticipantResponse> getParticipant(
                                                              @PathVariable Long tournamentID, @PathVariable Long teamID,
                                                              @PathVariable Long participantId) {
        Participant participant = participantService.findById(participantId).orElseThrow(() -> new NotFoundException("participant not found"));

        ParticipantResponse response = mapped(participant);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{tournamentID}/teams/{teamID}/participants")
    public ResponseEntity<Set<ParticipantResponse>> getParticipant(
            @PathVariable Long tournamentID, @PathVariable Long teamID) {
        Set<Participant> participants = participantService.findAllByTournamentIdAndTeamId(tournamentID, teamID);

        return ResponseEntity.status(HttpStatus.OK).body(participants.stream().map(this::mapped).collect(Collectors.toSet()));
    }

    @GetMapping("/{tournamentID}/participants")
    public ResponseEntity<PageResponse<ParticipantResponse>> getTournamentParticipant(
            @PathVariable Long tournamentID,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(defaultValue = "name") String sort) {

        Page<Participant> result = participantService.searchParticipants(tournamentID, page, size, sortDir, sort);
        List<ParticipantResponse> collected = result.get().map(this::mapped).collect(Collectors.toList());
        PageResponse<ParticipantResponse> response = PageResponse.build(size, page, result.getTotalElements(), collected);
        return ResponseEntity.status(HttpStatus.OK).body(response);
//        return ResponseEntity.status(HttpStatus.OK).body(participants.stream().map(this::mapped).collect(Collectors.toSet()));
    }

    @PutMapping("/{tournamentID}/teams/{teamID}/participants/{participantId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateParticipant(@AuthenticationPrincipal UserPrincipal principal,
                                                              @PathVariable Long tournamentID, @PathVariable Long teamID,
                                                              @PathVariable Long participantId, @RequestBody ParticipantRequest request) {

        participantService.updateParticipant(principal.getUserId(), participantId, tournamentID, teamID, request);
    }


    @PutMapping("/{tournamentID}/participants/{participantId}/check_in")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateParticipant(@AuthenticationPrincipal UserPrincipal principal,
                                  @PathVariable Long tournamentID,
                                  @PathVariable Long participantId) {

        participantService.updateParticipantCheckIn(principal.getUserId(), participantId, tournamentID);
    }

    @DeleteMapping("/{tournamentID}/participants/{participantId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteParticipant(@AuthenticationPrincipal UserPrincipal principal,
                                  @PathVariable Long tournamentID,
                                  @PathVariable Long participantId) {

        participantService.deleteParticipant(principal.getUserId(), participantId, tournamentID);
    }

    private ParticipantResponse mapped(Participant participant) {
        ParticipantResponse.ParticipantResponseBuilder response = ParticipantResponse.builder();
        response.participantId(participant.getId());
        response.email(participant.getEmail());
        response.sex(participant.getSex());
        response.name(participant.getName());
        response.lastName(participant.getLastName());
        response.height(participant.getHeight());
        response.weight(participant.getWeight());
        response.birthDate(participant.getBirthDate());
        response.status(participant.getStatus().name());

        response.belt(BeltCategoryResponse
                .builder()
                .id(participant.getBeltCategory().getId())
                .name(participant.getBeltCategory().getName())
                .build());

        List<ModalityResponse> modalities = new ArrayList<>(participant.getModalities().size());

        participant.getModalities().forEach(modality -> {
            modalities.add(ModalityResponseFactory.build(modality));
        });

        response.modalities(modalities);

        return response.build();
    }



}
