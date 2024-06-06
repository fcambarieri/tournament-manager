package com.fcctech.tournament.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParticipantTeamRequest {

    @NonNull
    private String associationName;
    @NonNull
    private String email;

}
