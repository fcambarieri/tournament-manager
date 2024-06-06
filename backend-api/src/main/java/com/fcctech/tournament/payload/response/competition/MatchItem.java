package com.fcctech.tournament.payload.response.competition;

import com.fcctech.tournament.domain.competition.MatchStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatchItem {
    private Long leftMatchId;
    private Long rightMatchId;
}
