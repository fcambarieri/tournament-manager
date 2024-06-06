package com.fcctech.tournament.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AccountRequest {

    private String associationName;
    private String phoneNumber;
    private String country;
    private String streetName;
    private String streetNumber;
    private String cityName;
    private String stateName;
}
