package com.fcctech.tournament.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "brackets")
@PrimaryKeyJoinColumn(name="id")
public class BracketCompetition extends Competition{


    @OneToMany(mappedBy = "bracket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Match> matches;

    @Override
    public int size() {
        return matches.size();
    }
}
