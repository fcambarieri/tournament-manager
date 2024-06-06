package com.fcctech.tournament.controller;

import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.entity.User;
import com.fcctech.tournament.payload.request.TournamentRequest;
import com.fcctech.tournament.payload.response.PageResponse;
import com.fcctech.tournament.payload.response.TournamentResponse;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.ParticipantService;
import com.fcctech.tournament.service.TournamentService;
import com.fcctech.tournament.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private UserService userService;

    @PostMapping("/tournaments")
    public ResponseEntity<Map> createTournament(@AuthenticationPrincipal UserPrincipal principal, @RequestBody TournamentRequest request) {
        Optional<User> user =  userService.findById(principal.getUserId());
        Tournament tournament = tournamentService.createTournament(request, user.orElseThrow());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", tournament.getId(), "status", tournament.getStatus()));
    }

    @PutMapping("/tournaments/{id}")
    public ResponseEntity updateTournament(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long id, @Valid  @RequestBody TournamentRequest request) {
        Optional<User> user =  userService.findById(principal.getUserId());
        Optional<Tournament> tournament = tournamentService.findById(id);
        if (tournament.isEmpty()) {
            return ControllerUtil.notFound("tournament not found");
        }
        if (!user.get().getId().equals(tournament.get().getOwner().getId())) {
            return ControllerUtil.accessDenied("forbidden access to tournament");
        }
        tournamentService.updateTournament(tournament.get(), request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/tournaments/{id}")
    public ResponseEntity getTournament(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long id) {
       Optional<Tournament> tournament = tournamentService.findById(id);
       if (tournament.isEmpty()) {
           return ControllerUtil.notFound("tournament not found");
       }
       Tournament t = tournament.get();
       return ResponseEntity.status(HttpStatus.OK)
               .body(TournamentResponse.builder()
                       .name(t.getName())
                       .dateCreated(t.getDateCreated())
                       .dateStarted(t.getDateStarted())
                       .dateEnd(t.getDateEnd())
                       .description(t.getDescription())
                       .dateUpdated(t.getDateUpdated())
                       .status(t.getStatus())
                       .build());

    }
    @GetMapping("/tournaments")
    public ResponseEntity<PageResponse<TournamentResponse>> getTournament(@AuthenticationPrincipal UserPrincipal principal,  @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "20") int size,
                                        @RequestParam(defaultValue = "desc") String sortDir,
                                        @RequestParam(defaultValue = "name") String sort) {
        Page<Tournament> result = tournamentService.listOwnerTournament(principal.getUserId(), page, size, sortDir, sort);
        List<TournamentResponse> collected = result.get().map(this::from).collect(Collectors.toList());
        PageResponse<TournamentResponse> response = PageResponse.build(size, page, result.getTotalElements(), collected);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




    public TournamentResponse from(Tournament t) {
        return TournamentResponse.builder()
                .name(t.getName())
                .dateCreated(t.getDateCreated())
                .dateStarted(t.getDateStarted())
                .dateEnd(t.getDateEnd())
                .description(t.getDescription())
                .dateUpdated(t.getDateUpdated())
                .status(t.getStatus())
                .build();
    }
}
