package com.fcctech.tournament.exception;

import org.springframework.http.HttpStatus;

public class TournamentAccessException extends BusinessException {

    public TournamentAccessException() {
        super("tournament access denied", HttpStatus.FORBIDDEN);
    }
}
