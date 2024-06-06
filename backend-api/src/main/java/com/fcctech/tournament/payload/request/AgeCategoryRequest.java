package com.fcctech.tournament.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AgeCategoryRequest {

    private String name;

    private Integer minAge;

    private Integer maxAge;
}
