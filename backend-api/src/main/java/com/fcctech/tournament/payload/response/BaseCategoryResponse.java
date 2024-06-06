package com.fcctech.tournament.payload.response;

import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.domain.competition.CompetitionFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public abstract class BaseCategoryResponse {

    private Long id;
    private String name;
    private SexType sex;
    private Integer maxParticipant;
    private ModalityType type;
    private CompetitionFormat format;
}
