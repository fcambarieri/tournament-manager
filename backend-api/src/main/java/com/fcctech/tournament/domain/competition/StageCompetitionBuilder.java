package com.fcctech.tournament.domain.competition;

import com.fcctech.tournament.domain.CompetitionStatus;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.payload.response.competition.CompositionKey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StageCompetitionBuilder implements CompetitionBuilder <StageCompetition>{

    private static final int defaultRounds = 3;
    @Override
    public StageCompetition createCompetition(Tournament tournament, CompositionKey key, List<Participant> participants) {
        StageCompetition competition = new StageCompetition();
        competition.setSex(key.getSex());
        competition.setCategory(key.getBaseCategory());
        competition.setStatus(CompetitionStatus.READY);
        competition.setBeltCategory(key.getBelt());
        competition.setType(key.getModality());
        competition.setTournament(tournament);
        competition.setNumberRounds(numberOfRounds(participants.size()));
        competition.setStages(new ArrayList<>(competition.getNumberRounds()));
        Iterator<Participant> it = participants.iterator();
        CompetitionStatus status = CompetitionStatus.READY;
        for (int i = competition.getNumberRounds(); i >= 1; i--) {
            Stage stage = new Stage();
            stage.setStage(competition);
            stage.setRound(i);
            stage.setParticipants(new ArrayList<>());
            stage.setStatus(status);
            while (it.hasNext()) {
                ParticipantStage participantStage = new ParticipantStage();
                participantStage.setStage(stage);
                participantStage.setParticipant(it.next());
                stage.getParticipants().add(participantStage);
            }
            competition.getStages().add(stage);
            status = CompetitionStatus.BLOCKED;
        }
        return competition;
    }

    public int numberOfRounds(int sizeCompetitors) {
        if (sizeCompetitors <= 8) {
            return 1;
        } else {
            return defaultRounds;
        }
    }

}
