package com.fcctech.tournament.controller;

import com.fcctech.tournament.entity.Competition;
import com.fcctech.tournament.entity.Match;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.exception.TournamentNotFoundException;
import com.fcctech.tournament.payload.request.MatchRequest;
import com.fcctech.tournament.payload.response.competition.CompetitionResponse;
import com.fcctech.tournament.payload.response.PageResponse;
import com.fcctech.tournament.payload.response.competition.CompetitionResponseBuilder;
import com.fcctech.tournament.payload.response.competition.MatchResponse;
import com.fcctech.tournament.payload.response.competition.TournamentComposition;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.CompetitionService;
import com.fcctech.tournament.service.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/tournaments/{tournamentId}/competitions_preview")
    @ResponseStatus(HttpStatus.OK)
    public Set<TournamentComposition> getTournamentComposition(@PathVariable Long tournamentId) {
        return competitionService.getTournamentComposition(tournamentId);
    }

    @GetMapping("/tournaments/{tournamentId}/competitions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PageResponse<CompetitionResponse>> getTournament(
            @PathVariable("tournamentId") Long tournamentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(defaultValue = "type") String sort) {

        Page<Competition> result = competitionService.listTournamentCompetitions(tournamentId, page, size, sortDir, sort);
        List<CompetitionResponse> collected = result.get().map(CompetitionResponseBuilder::build).collect(Collectors.toList());
        PageResponse<CompetitionResponse> response = PageResponse.build(size, page, result.getTotalElements(), collected);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/tournaments/{tournamentId}/competitions/{competitionId}")
    @ResponseStatus(HttpStatus.OK)
    public CompetitionResponse getCompetition(
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("competitionId") Long competitionId) {
        Competition competition = competitionService.findCompetitionById(tournamentId, competitionId).orElseThrow(() -> new NotFoundException("competition not found"));
        return CompetitionResponseBuilder.build(competition);
    }

    @PutMapping("/tournaments/{tournamentId}/competitions/{competitionId}/matches/{matchId}")
    @ResponseStatus(HttpStatus.OK)
    public Map updateCompetition(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("competitionId") Long competitionId,
            @PathVariable("matchId") Long matchId,
            @RequestBody MatchRequest request) {

        Optional<Match> nextMatch = competitionService.updateMatch(principal.getUserId(), tournamentId, competitionId, matchId, request);
        return nextMatch.isPresent()
                ? Map.of("next_match", Map.of("id", nextMatch.get().getId()))
                : Map.of("next_match", null);
    }

    @GetMapping("/tournaments/{tournamentId}/competitions/{competitionId}/matches/{matchId}")
    @ResponseStatus(HttpStatus.OK)
    public MatchResponse getMatch( @PathVariable("tournamentId") Long tournamentId,
                                   @PathVariable("competitionId") Long competitionId,
                                   @PathVariable("matchId") Long matchId) {
        Match match = competitionService.findMatch(tournamentId, competitionId, matchId);
        return CompetitionResponseBuilder.fromMatch(match);
    }




    @PostMapping("/tournaments/{tournamentId}/start")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> createCompetitions(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.findById(tournamentId).orElseThrow(TournamentNotFoundException::new);
        tournament.assertOwner(principal.getUserId());
        CompletableFuture.runAsync(() -> {
            competitionService.createCompetitions(tournamentId);
        });
        return Map.of("status", "in_process");
    }

}
