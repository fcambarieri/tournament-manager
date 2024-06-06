package com.fcctech.tournament.entity;

import com.fcctech.tournament.exception.TournamentAccessException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String name;
    @Column(length = 300)
    private String description;

    private Date dateStarted;
    private Date dateEnd;

    private Date dateCreated;
    private Date dateUpdated;

    private String streetAddress;
    private String streetNumber;
    private String cityName;
    private String stateName;
    private String countryCode;

    private String geolocation;

    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    @ManyToOne
    @JoinColumn(name="type", nullable=false)
    private TournamentType tournamentType;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private User owner;

    public void assertOwner(Long userId) {
        if (!owner.getId().equals(userId)) {
            throw new TournamentAccessException();
        }
    }
//-33.25452617615718, -66.34275471642074
}
