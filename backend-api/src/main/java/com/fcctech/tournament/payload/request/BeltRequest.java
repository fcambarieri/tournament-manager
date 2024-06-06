package com.fcctech.tournament.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BeltRequest {

    private String name;

    public BeltRequest() {

    }

    public BeltRequest(String name) {
        this.name = name;
    }
}
