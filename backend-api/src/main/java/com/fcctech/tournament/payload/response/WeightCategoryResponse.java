package com.fcctech.tournament.payload.response;

import com.fcctech.tournament.domain.ModalityType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeightCategoryResponse {
    private Long id;
    private Integer minWeight;
    private Integer maxWeight;
    private ModalityType modalityType;
    private AgeCategoryResponse ageCategory;
}
