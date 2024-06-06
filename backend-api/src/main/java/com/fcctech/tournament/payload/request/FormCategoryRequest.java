package com.fcctech.tournament.payload.request;

import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FormCategoryRequest {

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SexType sex;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull
    private ModalityType type;

    @NotNull
    private Integer maxParticipants;

    @NotNull
    private Integer minAge;

    @NotNull
    private Integer maxAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    @NotNull
    private CompetitionFormat format;
}
