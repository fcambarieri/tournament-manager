package com.fcctech.tournament.payload.response;

import com.fcctech.tournament.domain.ModalityType;
import lombok.Builder;
import lombok.Getter;

public interface ModalityResponse {
    Long getId();

    ModalityType getType();
}

