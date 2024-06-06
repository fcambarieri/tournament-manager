package com.fcctech.tournament.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.domain.competition.CompetitionFormat;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FormCategoryResponse extends BaseCategoryResponse{
    private IntPair agesRange;

    @Builder
    public FormCategoryResponse(Long id,
                                  String name,
                                  SexType sex,
                                  Integer maxParticipant,
                                  ModalityType type,
                                  CompetitionFormat format,
                                  IntPair agesRange
                                  ) {
        super(id, name, sex, maxParticipant, type, format);
        this.agesRange = agesRange;
    }
}
