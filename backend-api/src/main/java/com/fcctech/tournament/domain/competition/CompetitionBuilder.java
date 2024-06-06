package com.fcctech.tournament.domain.competition;

import com.fcctech.tournament.entity.Competition;
import com.fcctech.tournament.entity.Participant;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.payload.response.competition.CompositionKey;

import java.util.List;

public interface CompetitionBuilder<T extends Competition> {
    T createCompetition(Tournament tournament, CompositionKey key, List<Participant> participants);

}
