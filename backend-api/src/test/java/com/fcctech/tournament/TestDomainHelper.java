package com.fcctech.tournament;

import com.fcctech.tournament.domain.*;
import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.payload.request.AgeCategoryRequest;
import com.fcctech.tournament.payload.request.ModalityRequest;
import com.fcctech.tournament.payload.request.ParticipantRequest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class TestDomainHelper {

    public static Tournament defaultTournament() {
        Tournament t = new Tournament();
        t.setId(1L);
        t.setName("open");
        return t;
    }

    public static BeltCategory defaultBeltCategory() {
        BeltCategory b = new BeltCategory();
        b.setId(1L);
        b.setName("dan");
        return b;
    }

    public static ParticipantTeam defaultParticipantTeam() {
        ParticipantTeam team = new ParticipantTeam();
        team.setId(1L);
        team.setName("Jaguars");
        team.setTournament(defaultTournament());
        return team;
    }

    public static Participant defaultParticipant(Long id, Tournament t) {
        Participant p = new Participant();
        p.setId(id);
        p.setTournament(t);
        p.setStatus(ParticipantStatus.PENDING_VALIDATION);
        p.setEmail("fcamb@gmail.com");
        p.setWeight(84D);
        p.setName("fer");
        p.setLastName("capo");
        p.setBeltCategory(defaultBeltCategory());
        p.setModalities(new HashSet<>());
        p.setTeam(defaultParticipantTeam());
        return p;
    }

    public static AgeCategory defaultAgeCategory() {
        AgeCategory ageCategory = new AgeCategory();
        ageCategory.setId(1L);
        ageCategory.setName("Seniors");
        ageCategory.setMinAge(18);
        ageCategory.setMaxAge(50);
        return ageCategory;
    }

    public static CombatCategory defaultFightCategory() {
        CombatCategory fightCategory = new CombatCategory();
        fightCategory.setId(1L);
        fightCategory.setSex(SexType.MALE);
        fightCategory.setMaxParticipant(1);
        fightCategory.setType(ModalityType.COMBAT);
        fightCategory.setMinWeight(80);
        fightCategory.setMaxWeight(100);
        fightCategory.setAgeCategory(defaultAgeCategory());
        fightCategory.setFormat(CompetitionFormat.SINGLE_ELIMINATION);
        return fightCategory;
    }

    public static Modality defaultModality() {
        Modality modality = new Modality();
        modality.setType(ModalityType.COMBAT);
        modality.setCategory(defaultFightCategory());
        modality.setId(1L);
        return modality;
    }

    public static ParticipantRequest defaultParticipantRequest() {
        return ParticipantRequest.builder()
                .sex(SexType.MALE)
                .email("f@y")
                .name("fer")
                .weight(80D)
                .lastName("camba")
                .beltCategoryId(1L)
                .dateBirth(LocalDate.of(1980, 12, 30))
                .modalities(List.of(ModalityRequest.builder()
                        .categoryId(1L)
                        .type(ModalityType.COMBAT)
                        .action(Optional.of(Action.NEW))
                        .build()))
                .build();
    }

    public static AgeCategoryRequest defaultAgeCategoryRequest() {
        return AgeCategoryRequest.builder()
                .name("adults")
                .minAge(18)
                .maxAge(35)
                .build();
    }
}
