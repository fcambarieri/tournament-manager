package com.fcctech.tournament.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tournament_type")
public class TournamentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String name;
}
