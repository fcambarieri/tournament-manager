package com.fcctech.tournament.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import org.springframework.lang.NonNull;

@Getter
@Builder
public class TournamentRequest {

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull
    private String type;
    @NotNull
    private Date dateStarted;
    @NotNull
    private Date dateEnd;
    private String status;
    private String description;
    private String imageUrl;

}
