package com.fcctech.tournament.entity;

import com.fcctech.tournament.domain.CompetitionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "stages")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stage_competition_id")
    private StageCompetition stage;

    private Integer round;

    @OneToMany(mappedBy = "stage", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ParticipantStage> participants;

    private CompetitionStatus status;
}
