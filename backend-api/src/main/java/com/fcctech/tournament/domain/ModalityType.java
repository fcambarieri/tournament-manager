package com.fcctech.tournament.domain;

public enum ModalityType {
    COMBAT(true, false),
    COMBAT_GROUP(true, true),
    FORM (false, false),
    FORM_GROUP(false, true),
    FORM_FREE_STYLE(false, false),
    FORM_FREE_STYLE_GROUP(false, true),
    BREAKING;

    private final boolean weightNeedIt;
    private final boolean isTeamType;

    private ModalityType() {
        this(false, false);
    }
    private ModalityType(boolean weightNeedIt, boolean isTeamType) {
        this.weightNeedIt = weightNeedIt;
        this.isTeamType = isTeamType;
    }

    public boolean isWeightNeedIt() {
        return weightNeedIt;
    }

    public boolean isTeamType() {
        return isTeamType;
    }
}
