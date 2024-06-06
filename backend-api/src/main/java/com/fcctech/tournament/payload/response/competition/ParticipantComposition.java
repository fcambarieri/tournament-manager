package com.fcctech.tournament.payload.response.competition;

import com.fcctech.tournament.entity.Participant;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ParticipantComposition {
    private Long id;
    private String name;
    private String lastName;
    private String team;

    @Transient
    private Participant participant;
}
