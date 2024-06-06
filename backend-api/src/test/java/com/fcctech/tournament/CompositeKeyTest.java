package com.fcctech.tournament;

import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.payload.response.competition.CompositionKey;
import com.fcctech.tournament.service.impl.CompetitionServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CompositeKeyTest {

    private BeltCategory dan;
    private BeltCategory gup9_7;

    private BeltCategory gup6_4;

    private AgeCategory adults;
    private AgeCategory juvenil;

    private AgeCategory cadate;

    private CombatCategory combatCategoryCadete;

    private CombatCategory combatCategoryJuvenil;


    private CombatCategory combatCategoryAdults;
    private CombatCategory combatCategoryAdults80kg;


    @BeforeEach
    public void setup() {
        dan = new BeltCategory();
        dan.setId(1L);
        dan.setName("dan");

        gup9_7 = new BeltCategory();
        gup9_7.setId(1L);
        gup9_7.setName("9-7 gup");

        gup6_4 = new BeltCategory();
        gup6_4.setId(1L);
        gup6_4.setName("6-4 gup");

        adults = new AgeCategory();
        adults.setId(1L);
        adults.setName("adults");
        adults.setMinAge(18);
        adults.setMaxAge(35);

        juvenil = new AgeCategory();
        juvenil.setId(2L);
        juvenil.setName("juvenil");
        juvenil.setMinAge(14);
        juvenil.setMaxAge(17);

        cadate = new AgeCategory();
        cadate.setId(3L);
        cadate.setName("adults");
        cadate.setMinAge(11);
        cadate.setMaxAge(13);

        combatCategoryCadete = new CombatCategory();
        combatCategoryCadete.setId(1L);
        combatCategoryCadete.setFormat(CompetitionFormat.SINGLE_ELIMINATION);
        combatCategoryCadete.setType(ModalityType.COMBAT);
        combatCategoryCadete.setMinWeight(35);
        combatCategoryCadete.setMaxWeight(45);
        combatCategoryCadete.setAgeCategory(cadate);
        combatCategoryCadete.setSex(SexType.MALE);


        combatCategoryJuvenil = new CombatCategory();
        combatCategoryJuvenil.setId(2L);
        combatCategoryJuvenil.setFormat(CompetitionFormat.SINGLE_ELIMINATION);
        combatCategoryJuvenil.setType(ModalityType.COMBAT);
        combatCategoryJuvenil.setMinWeight(35);
        combatCategoryJuvenil.setMaxWeight(45);
        combatCategoryJuvenil.setAgeCategory(juvenil);
        combatCategoryJuvenil.setSex(SexType.MALE);

        combatCategoryAdults = new CombatCategory();
        combatCategoryAdults.setId(3L);
        combatCategoryAdults.setFormat(CompetitionFormat.SINGLE_ELIMINATION);
        combatCategoryAdults.setType(ModalityType.COMBAT);
        combatCategoryAdults.setMinWeight(35);
        combatCategoryAdults.setMaxWeight(45);
        combatCategoryAdults.setAgeCategory(adults);
        combatCategoryAdults.setSex(SexType.MALE);

        combatCategoryAdults80kg = new CombatCategory();
        combatCategoryAdults80kg.setId(4L);
        combatCategoryAdults80kg.setFormat(CompetitionFormat.SINGLE_ELIMINATION);
        combatCategoryAdults80kg.setType(ModalityType.COMBAT);
        combatCategoryAdults80kg.setMinWeight(46);
        combatCategoryAdults80kg.setMaxWeight(50);
        combatCategoryAdults80kg.setAgeCategory(adults);
        combatCategoryAdults80kg.setSex(SexType.MALE);
    }

    @Test
    public void test_composition_key_order() {
        Map<CompositionKey, List<Long>> map = new HashMap<>();
        map.put(CompositionKey.builder()
                .sex(SexType.MALE)
                .belt(dan)
                .modality(ModalityType.COMBAT)
                .baseCategory(combatCategoryJuvenil)
                .categoryId(combatCategoryJuvenil.getId())
                .build(), List.of(1L, 2L, 3L));
        map.put(CompositionKey.builder()
                .sex(SexType.MALE)
                .belt(dan)
                .modality(ModalityType.COMBAT)
                .baseCategory(combatCategoryCadete)
                .categoryId(combatCategoryCadete.getId())
                .build(), List.of(1L, 2L, 3L));
        map.put(CompositionKey.builder()
                .sex(SexType.MALE)
                .belt(dan)
                .modality(ModalityType.COMBAT)
                .baseCategory(combatCategoryAdults)
                .categoryId(combatCategoryAdults.getId())
                .build(), List.of(1L, 2L, 3L, 6L));

        TreeMap<CompositionKey, List<Long>> tree = new TreeMap<>(map);
        System.out.println(tree);
    }

    @Test
    public void test_sort_competitions_match() {
        List<Competition> competitions = new ArrayList<>();
        BracketCompetition bc = new BracketCompetition();
        bc.setCategory(combatCategoryAdults);
        bc.setMatches(new ArrayList<>());

        Match m = new Match();
        m.setId(1L);
        m.setBracket(bc);
        m.setRound(3);
        bc.getMatches().add(m);

        m = new Match();
        m.setBracket(bc);
        m.setRound(2);
        m.setId(2L);
        bc.getMatches().add(m);

        competitions.add(bc);

        bc = new BracketCompetition();
        bc.setCategory(combatCategoryJuvenil);
        bc.setMatches(new ArrayList<>());

        m = new Match();
        m.setId(3L);
        m.setBracket(bc);
        m.setRound(1);
        bc.getMatches().add(m);

        m = new Match();
        m.setId(4L);
        m.setBracket(bc);
        m.setRound(2);
        bc.getMatches().add(m);

        competitions.add(bc);

        bc = new BracketCompetition();
        bc.setCategory(combatCategoryAdults80kg);
        bc.setMatches(new ArrayList<>());
        m = new Match();
        m.setId(5L);
        m.setBracket(bc);
        m.setRound(1);
        bc.getMatches().add(m);

        m = new Match();
        m.setId(6L);
        m.setBracket(bc);
        m.setRound(2);
        bc.getMatches().add(m);

        m = new Match();
        m.setId(7L);
        m.setBracket(bc);
        m.setRound(3);
        bc.getMatches().add(m);
        competitions.add(bc);

        CompetitionServiceImp serviceImp = new CompetitionServiceImp(null, null, null, null);
        serviceImp.sortAndEnumerateCompetitionMatch(competitions, 10);
        for (int i = 0; i < competitions.size(); i++) {
            BracketCompetition bracket = ((BracketCompetition)competitions.get(i));
            for (int j = 0; j < bracket.size(); j++) {
                Match match = bracket.getMatches().get(j);
                System.out.println(String.format("%d ) %s round: %d match_nro: %d *> ID: %d", i,
                                competitions.get(i).getCategory().display(),
                                match.getRound(),
                                match.getNumber(), match.getId()));
            }
        }
    }
}
