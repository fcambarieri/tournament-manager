package com.fcctech.tournament.service;

import com.fcctech.tournament.entity.Competition;
import com.fcctech.tournament.entity.Match;
import com.fcctech.tournament.entity.Participant;
import com.fcctech.tournament.payload.request.MatchRequest;
import com.fcctech.tournament.payload.response.competition.CompositionKey;
import com.fcctech.tournament.payload.response.competition.TournamentComposition;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface CompetitionService {

    Set<TournamentComposition> getTournamentComposition(Long tournamentId);

    Map<CompositionKey, List<Participant>> getTournamentCompositionMap(Long tournamentId);

    void createCompetitions(Long tournamentId);

    Page<Competition> listTournamentCompetitions(Long tournamentId, int page, int size, String sortDir, String sort);

    Optional<Competition> findCompetitionById(Long tournamentId, Long competitionId);

    Optional<Match> updateMatch(Long userId, Long tournamentId, Long competitionId, Long matchId, MatchRequest request);

    Match findMatch(Long tournamentId, Long competitionId, Long matchId);
}
