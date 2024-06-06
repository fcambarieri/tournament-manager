package com.fcctech.tournament.payload.response;

import com.fcctech.tournament.domain.SexType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class ParticipantResponse {
    private Long participantId;
    private String name;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private Double weight;
    private Integer height;
    private SexType sex;
    private BeltCategoryResponse belt;
    private String status;
    private List<ModalityResponse> modalities;
}
