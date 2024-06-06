package com.fcctech.tournament.payload.response;

import com.fcctech.tournament.entity.TournamentStatus;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class TournamentResponse {
    private String name;
    private String description;
    private Date dateStarted;
    private Date dateEnd;
    private Date dateCreated;
    private Date dateUpdated;
    private TournamentStatus status;
}
