package com.fcctech.tournament.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAccountResponse {
    private String email;
    private String associationName;
    private String phoneNumber;
    private String country;
    private String streetName;
    private String streetNumber;
    private String cityName;
    private String stateName;
}
