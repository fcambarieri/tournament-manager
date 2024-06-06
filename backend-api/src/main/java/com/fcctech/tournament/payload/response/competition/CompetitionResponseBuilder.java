package com.fcctech.tournament.payload.response.competition;

import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.exception.BusinessException;
import com.fcctech.tournament.payload.response.*;
import org.springframework.http.HttpStatus;

import java.util.stream.Collectors;

public class CompetitionResponseBuilder {

    public static CompetitionResponse build(Competition competition) {
        return switch (competition.getClass().getSimpleName()) {
            case "BracketCompetition" -> seBuild((BracketCompetition) competition);
            case "StageCompetition" -> scBuild((StageCompetition) competition);
            default -> throw new BusinessException("none builder found", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

    private static SingleEliminationCompetitionResponse seBuild(BracketCompetition competition) {
        return SingleEliminationCompetitionResponse.builder()
                .id(competition.getId())
                .tournamentId(competition.getTournament().getId())
                .belt(BeltCategoryResponse.builder()
                        .id(competition.getBeltCategory().getId())
                        .name(competition.getBeltCategory().getName())
                        .build())
                .status(competition.getStatus())
                .category(buildBaseCategory(competition.getCategory()))
                .matches(competition.getMatches().stream().map(match -> fromMatch(match)).collect(Collectors.toList()))
                .build();
    }

    public static MatchResponse fromMatch(Match match) {
        return MatchResponse.builder()
                .id(match.getId())
                .end(match.getEnd())
                .start(match.getStart())
                .round(match.getRound())
                .nextMatchId(match.nextMatchId())
                .number(match.getNumber())
                .status(match.getStatus())
                .dependsOnMatches(MatchItem.builder()
                        .leftMatchId(match.leftMatchId())
                        .rightMatchId(match.rightMatchId())
                        .build())
                .winner(match.winnerId())
                .participantBlue(participant(match.getRight()))
                .participantRed(participant(match.getLeft()))
                .build();
    }

    private static ParticipantResponse participant(Participant participant) {
        return participant != null
                ? ParticipantResponse
                .builder()
                .id(participant.getId())
                .lastName(participant.getLastName())
                .name(participant.getName())
                .team(participant.getTeam().getName())
                .build()
                : null;
    }

    private static StageCompetitionResponse scBuild(StageCompetition competition) {
        return StageCompetitionResponse.builder()
                .build();
    }

    private static BaseCategoryResponse buildBaseCategory(BaseCategory category) {
        return switch (category.getClass().getSimpleName()) {
            case "CombatCategory" -> combatResponse((CombatCategory) category);
            case "FormCategory" -> formResponse((FormCategory) category);
            default -> throw new BusinessException("category not defined", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

    private static CombatCategoryResponse combatResponse(CombatCategory category) {
        return CombatCategoryResponse.builder()
                .name(category.display())
                .type(category.getType())
                .sex(category.getSex())
                .format(category.getFormat())
                .id(category.getId())
                .ageCategory(category.getAgeCategory().getId())
                .agesRange(IntPair.builder()
                        .from(category.getAgeCategory().getMinAge())
                        .to(category.getAgeCategory().getMaxAge())
                        .build())
                .weightRange(IntPair.builder()
                        .from(category.getMinWeight())
                        .to(category.getMaxWeight())
                        .build())
                .build();
    }

    private static FormCategoryResponse formResponse(FormCategory category) {
        return FormCategoryResponse.builder()
                .type(category.getType())
                .sex(category.getSex())
                .format(category.getFormat())
                .id(category.getId())
                .agesRange(IntPair.builder()
                        .from(category.getMinAge())
                        .to(category.getMaxAge())
                        .build())
                .build();
    }
}
