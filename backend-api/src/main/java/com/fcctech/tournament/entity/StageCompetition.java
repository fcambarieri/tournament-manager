package com.fcctech.tournament.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "competition_stages")
@PrimaryKeyJoinColumn(name="id")
public class StageCompetition extends Competition {

    private int numberRounds;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Stage> stages;

    @Override
    public int size() {
        return stages.size();
    }
}
