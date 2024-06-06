package com.fcctech.tournament.payload.response.competition;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParticipantResponse {
    private Long id;
    private String name;
    private String lastName;
    private String team;
}
