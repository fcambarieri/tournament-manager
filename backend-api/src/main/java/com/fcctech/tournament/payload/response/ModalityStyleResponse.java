package com.fcctech.tournament.payload.response;

import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModalityStyleResponse implements ModalityResponse {
    private Long id;
    private ModalityType type;
    private String name;
    private Long categoryId;
    private IntPair agesRange;
}
