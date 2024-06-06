package com.fcctech.tournament.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {
    @JsonProperty("email")
    private  String emailOrUserName;
    private  String password;
}
