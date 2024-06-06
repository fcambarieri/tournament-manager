package com.fcctech.tournament.payload.request;

import com.fcctech.tournament.domain.Action;
import com.fcctech.tournament.domain.ModalityType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
@Builder
public class ModalityRequest {
    private ModalityType type;
    private Long categoryId;
    private Long id;
    private Optional<Action> action;
}
