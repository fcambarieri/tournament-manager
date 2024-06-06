package com.fcctech.tournament.exception;

public class TournamentNotFoundException extends NotFoundException{
    public TournamentNotFoundException() {
        super("tournament not found");
    }
}
