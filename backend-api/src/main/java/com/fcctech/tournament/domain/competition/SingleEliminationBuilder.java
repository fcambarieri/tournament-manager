package com.fcctech.tournament.domain.competition;

import com.fcctech.tournament.domain.CompetitionStatus;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.payload.response.competition.CompositionKey;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SingleEliminationBuilder implements CompetitionBuilder<BracketCompetition> {
    @Override
    public BracketCompetition createCompetition(Tournament tournament, CompositionKey composition, List<Participant> participants) {
        BracketCompetition bracketCompetition = new BracketCompetition();
        bracketCompetition.setBeltCategory(composition.getBelt());
        bracketCompetition.setSex(composition.getSex());
        bracketCompetition.setType(composition.getModality());
        bracketCompetition.setTournament(tournament);
        bracketCompetition.setCategory(composition.getBaseCategory());
        bracketCompetition.setMatches(new ArrayList<>());
        bracketCompetition.setStatus(CompetitionStatus.READY);
        createMatch(bracketCompetition, 1, null, participants);
        return bracketCompetition;
    }

    public Match createMatch(BracketCompetition bracketCompetition, int round, Match nextMatch, List<Participant> participants) {
        if (nextMatch == null) {
            nextMatch = new Match();
            nextMatch.setBracket(bracketCompetition);
            nextMatch.setRound(round);
            nextMatch.setStatus(MatchStatus.WAITING_MATCH);
            bracketCompetition.getMatches().add(nextMatch);
        }

        int participantSize = participants.size();
        if (participantSize == 2) {
            nextMatch.setLeft(participants.get(0));
            nextMatch.setRight(participants.get(1));
            nextMatch.setStatus(MatchStatus.READY);
        } else if (participantSize == 3) {
            nextMatch.setLeft(participants.remove(0));
            nextMatch.setLeftMatch(createMatch(bracketCompetition, ++round, nextMatch.getLeftMatch(), participants));
        } else {
            boolean odd = participantSize % 2 != 0;
            int middle = participantSize / 2 ;
            if (odd) {
                middle++;
            }
            round += 1;
            nextMatch.setLeftMatch(createMatch(bracketCompetition, round, nextMatch.getLeftMatch(), participants.subList(0, middle)));
            nextMatch.setRightMatch(createMatch(bracketCompetition, round, nextMatch.getRightMatch(), participants.subList(middle, participantSize)));
        }

        //Setting Next before return
        if (nextMatch.getLeftMatch() != null) {
            nextMatch.getLeftMatch().setNext(nextMatch);
        }

        if (nextMatch.getRightMatch() != null) {
            nextMatch.getRightMatch().setNext(nextMatch);
        }

        return nextMatch;
    }

}
