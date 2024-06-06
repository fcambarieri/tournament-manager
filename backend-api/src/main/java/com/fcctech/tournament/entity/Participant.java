package com.fcctech.tournament.entity;

import com.fcctech.tournament.domain.ParticipantStatus;
import com.fcctech.tournament.domain.SexType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private Double weight;
    private Integer height;
    private String docType;
    private String docNumber;
    private String nationality;

    @Enumerated(EnumType.STRING)
    private SexType sex;

    @OneToMany(mappedBy="participant",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Modality> modalities;

    @ManyToOne
    @JoinColumn(name="participant_team_id", nullable=false)
    private ParticipantTeam team;

    @ManyToOne
    @JoinColumn(name="tournament_id", nullable=false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name="belt_category_id", nullable=false)
    private BeltCategory beltCategory;


    @Enumerated(EnumType.STRING)
    private ParticipantStatus status;

    public boolean isCategoryIdIn(Long categoryId) {
        if (modalities != null) {
            return false;
        }
        return modalities.stream().anyMatch(modality -> modality.getCategory().getId().equals(categoryId));
    }
}
