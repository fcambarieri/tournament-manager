package com.fcctech.tournament.entity;

public enum TournamentStatus {
    OPEN, CLOSED, IN_PROCESS;

    public static TournamentStatus from(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        for (TournamentStatus val : TournamentStatus.values()) {
            if (value.equalsIgnoreCase(val.name())) {
                return val;
            }
        }
        return null;
    }
}
