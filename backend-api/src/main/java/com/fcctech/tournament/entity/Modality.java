package com.fcctech.tournament.entity;

import com.fcctech.tournament.domain.ModalityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "participant_modalities")
public class Modality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="participant_id", nullable=false)
    private Participant participant;

    @Enumerated(EnumType.STRING)
    private ModalityType type;


    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private BaseCategory category;

}
