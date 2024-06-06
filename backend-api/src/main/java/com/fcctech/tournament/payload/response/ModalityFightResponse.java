package com.fcctech.tournament.payload.response;

import com.fcctech.tournament.domain.ModalityType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModalityFightResponse implements ModalityResponse{
    private Long id;
    private ModalityType type;
    private Long categoryId;
    private String name;
    private IntPair weightRange;
    private IntPair ageRange;

}
