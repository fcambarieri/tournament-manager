package com.fcctech.tournament.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AgeCategoryResponse {
    private Long id;
    private String name;
    private Integer minAge;
    private Integer maxAge;
}
