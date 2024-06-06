package com.fcctech.tournament.payload.response.competition;

import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.payload.response.BeltCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class TournamentComposition {
    private String name;
    private BeltCategory belt;
    private SexType sex;
    private ModalityType modality;
    private Long categoryId;
    private Long count;
    private CompetitionFormat format;
    private Set<ParticipantComposition> participants;

    @Override
    public String toString() {
        return "TournamentComposition{" +
                "name='" + name + '\'' +
                ", " + belt +
                ", sex=" + sex +
                ", modality=" + modality +
                ", categoryId=" + categoryId +
                '}';
    }
}
