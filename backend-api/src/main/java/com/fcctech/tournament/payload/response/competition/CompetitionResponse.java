package com.fcctech.tournament.payload.response.competition;

import com.fcctech.tournament.domain.CompetitionStatus;
import com.fcctech.tournament.payload.response.BaseCategoryResponse;
import com.fcctech.tournament.payload.response.BeltCategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class CompetitionResponse {
    private Long id;
    private Long tournamentId;
    private BeltCategoryResponse belt;
    private CompetitionStatus status;
    private BaseCategoryResponse category;
}
