package com.fcctech.tournament.domain.competition;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public enum CompetitionFactory {
    INSTANCE;

    private Map<CompetitionFormat, CompetitionBuilder> map = Map.of(
            CompetitionFormat.SINGLE_ELIMINATION, new SingleEliminationBuilder(),
            CompetitionFormat.STAGES, new StageCompetitionBuilder()
    );

    public CompetitionBuilder getBuilder(@NotNull CompetitionFormat format) {
        return map.get(format);
    }
}
