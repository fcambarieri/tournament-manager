package com.fcctech.tournament.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.domain.competition.CompetitionFormat;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CombatCategoryResponse extends BaseCategoryResponse {
    private Long ageCategory;
    private IntPair weightRange;
    private IntPair agesRange;

    @Builder
    public CombatCategoryResponse(Long id,
                                  String name,
                                  SexType sex,
                                  Integer maxParticipant,
                                  ModalityType type,
                                  CompetitionFormat format,
                                  Long ageCategory,
                                  IntPair weightRange,
                                  IntPair agesRange) {
        super(id, name, sex, maxParticipant, type, format);
        this.ageCategory = ageCategory;
        this.weightRange = weightRange;
        this.agesRange = agesRange;
    }
}
