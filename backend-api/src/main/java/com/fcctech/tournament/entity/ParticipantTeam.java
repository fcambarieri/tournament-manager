package com.fcctech.tournament.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "participant_teams")
public class ParticipantTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name="tournament_id", nullable=false)
    private Tournament tournament;

    @OneToMany(mappedBy="team")
    private Set<Participant> participants;

    @OneToOne
    @JoinColumn(name="user_subscriber_id", nullable=false)
    private User subscriber;

}
