package com.fcctech.tournament.payload.response.competition;

import com.fcctech.tournament.domain.competition.MatchStatus;
import com.fcctech.tournament.entity.BracketCompetition;
import com.fcctech.tournament.entity.Match;
import com.fcctech.tournament.entity.Participant;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class MatchResponse {
    private Long id;
    private Long number;
    private LocalTime start;
    private LocalTime end;
    private Long nextMatchId;
    private MatchItem dependsOnMatches;
    private ParticipantResponse participantBlue;
    private ParticipantResponse participantRed;
    private Long winner;
    private MatchStatus status;
    private Integer round;
}
