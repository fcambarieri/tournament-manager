package com.fcctech.tournament.entity;

import com.fcctech.tournament.domain.CompetitionStatus;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "competitions")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Competition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Tournament tournament;

    @OneToOne
    private BeltCategory beltCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private SexType sex;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ModalityType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CompetitionStatus status;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "category_id")
    private BaseCategory category;

    public abstract int size();
}
