package com.fcctech.tournament.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String associationName;

    private String phoneNumber;

    private String streetName;

    private String streetNumber;

    private String cityName;

    private String stateName;

    private String countryCode;

    @OneToOne
    @JoinColumn(name="owner_id", nullable=false)
    private User owner;
}
