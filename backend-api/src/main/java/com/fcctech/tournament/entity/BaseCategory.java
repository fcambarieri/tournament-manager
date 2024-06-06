package com.fcctech.tournament.entity;

import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "base_categories")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private SexType sex;
    @Column(name = "num_participant")
    private Integer maxParticipant;

    @ManyToOne
    @JoinColumn(name="tournament_id", nullable=false)
    private Tournament tournament;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ModalityType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private CompetitionFormat format;
    @Transient
    public abstract String display();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseCategory that = (BaseCategory) o;

        if (!id.equals(that.id)) return false;
        if (sex != that.sex) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    public abstract Integer getMinYear();

    public abstract Integer getMaxYear();
}
