package com.fcctech.tournament.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class MatchRequest {

    private LocalTime start;
    private LocalTime end;
    private Long winner;
}
