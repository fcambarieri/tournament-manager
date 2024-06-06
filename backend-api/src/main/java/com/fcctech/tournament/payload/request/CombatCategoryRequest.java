package com.fcctech.tournament.payload.request;

import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CombatCategoryRequest {

    @NotNull
    private String name;
    @NotNull
    private Integer minWeight;
    @NotNull
    private Integer maxWeight;

    @Enumerated(EnumType.STRING)
    private SexType sex;

    @NotNull
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull
    private ModalityType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    @NotNull
    private CompetitionFormat format;

    @NotNull
    private Integer maxParticipants;
}
