package com.fcctech.tournament.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fcctech.tournament.domain.SexType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
public class ParticipantRequest {

    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateBirth;

    private Double weight;

    private Integer height;

    private SexType sex;

    private Long beltCategoryId;

    @NotEmpty
    private List<ModalityRequest> modalities;
}
