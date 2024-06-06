package com.fcctech.tournament.payload.response.competition;

import com.fcctech.tournament.payload.response.competition.CompetitionResponse;
import com.fcctech.tournament.payload.response.competition.MatchResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
public class SingleEliminationCompetitionResponse extends CompetitionResponse {
    private List<MatchResponse> matches;
}
