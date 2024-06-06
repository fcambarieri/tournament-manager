package com.fcctech.tournament.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "participant_stages")
public class ParticipantStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @OneToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    private Integer score;
}
