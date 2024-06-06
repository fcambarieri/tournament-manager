package com.fcctech.tournament.domain.competition;

public enum LevelRound {
    FINAL(1),
    SEMI_FINAL (2),
    QUARTER_FINAL(3),
    EIGHTHS_ROUND (4),
    SIXTEENTH_ROUND (5),
    THIRTIETH_ROUND (6),
    SIXTY_FOURTH_ROUND (7);

    private final int level ;

    private LevelRound(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}
