package com.fcctech.tournament.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BeltCategoryResponse {
    private Long id;
    private String name;
}
