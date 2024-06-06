package com.fcctech.tournament.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "age_categories")
public class AgeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer minAge;

    private Integer maxAge;

    @ManyToOne
    @JoinColumn(name="tournament_id", nullable=false)
    private Tournament tournament;

    public boolean isBirthInRange(int age) {
        return age >= minAge && age <= maxAge;
    }

    public boolean isNotBirthInRange(int age) {
        return !isBirthInRange(age);
    }

}
