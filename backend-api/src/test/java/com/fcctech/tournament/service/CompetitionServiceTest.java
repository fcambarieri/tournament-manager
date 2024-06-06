package com.fcctech.tournament.service;

import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.domain.competition.CompetitionFactory;
import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.ParticipantStatus;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.payload.response.competition.CompetitionResponseBuilder;
import com.fcctech.tournament.payload.response.competition.CompositionKey;
import com.fcctech.tournament.repository.CombatCategoryRepository;
import com.fcctech.tournament.repository.FormCategoryRepository;
import com.fcctech.tournament.repository.ParticipantRepository;
import com.fcctech.tournament.service.impl.CompetitionServiceImp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CompetitionServiceTest {

    @InjectMocks
    private CompetitionServiceImp dashboardServiceImp;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private CombatCategoryRepository combatCategoryRepository;

    @Mock
    private FormCategoryRepository formCategoryRepository;

    @Mock
    private EntityManager entityManager;

    private Tournament tournament;
    private BeltCategory beltCategory;
    private CombatCategory combatCategory;
    private CombatCategory combatCategory2;

    private CombatCategory combatCategoryFemale;
    private FormCategory formCategory;

    private ParticipantTeam participantTeam;

    @BeforeEach
    public void setUp() {
        tournament = new Tournament();
        tournament.setId(1L);

        beltCategory = new BeltCategory();
        beltCategory.setId(1L);
        beltCategory.setName("Dan");
        beltCategory.setTournament(tournament);

        AgeCategory ageCategory = new AgeCategory();
        ageCategory.setId(1L);
        ageCategory.setName("adults");
        ageCategory.setTournament(tournament);
        ageCategory.setMinAge(17);
        ageCategory.setMaxAge(30);

        combatCategory = new CombatCategory();
        combatCategory.setId(1L);
        combatCategory.setTournament(tournament);
        combatCategory.setAgeCategory(ageCategory);
        combatCategory.setType(ModalityType.COMBAT);
        combatCategory.setSex(SexType.MALE);
        combatCategory.setMinWeight(50);
        combatCategory.setMaxWeight(60);
        combatCategory.setFormat(CompetitionFormat.SINGLE_ELIMINATION);


        combatCategory2 = new CombatCategory();
        combatCategory2.setId(2L);
        combatCategory2.setTournament(tournament);
        combatCategory2.setAgeCategory(ageCategory);
        combatCategory2.setType(ModalityType.COMBAT);
        combatCategory2.setSex(SexType.MALE);
        combatCategory2.setMinWeight(61);
        combatCategory2.setMaxWeight(65);
        combatCategory2.setFormat(CompetitionFormat.SINGLE_ELIMINATION);


        combatCategoryFemale = new CombatCategory();
        combatCategoryFemale.setId(3L);
        combatCategoryFemale.setTournament(tournament);
        combatCategoryFemale.setAgeCategory(ageCategory);
        combatCategoryFemale.setType(ModalityType.COMBAT);
        combatCategoryFemale.setSex(SexType.FEMALE);
        combatCategoryFemale.setMinWeight(61);
        combatCategoryFemale.setMaxWeight(65);
        combatCategoryFemale.setFormat(CompetitionFormat.SINGLE_ELIMINATION);


        formCategory = new FormCategory();
        formCategory.setId(4L);
        formCategory.setMaxParticipant(1);
        formCategory.setMinAge(17);
        formCategory.setMaxAge(30);
        formCategory.setName("Under 30 years");
        formCategory.setFormat(CompetitionFormat.STAGES);

        participantTeam = new ParticipantTeam();
        participantTeam.setId(1L);
        participantTeam.setName("Escuela Andina");

        given(combatCategoryRepository.findById(1L)).willReturn(Optional.of(combatCategory));
        given(combatCategoryRepository.findById(2L)).willReturn(Optional.of(combatCategory2));
        given(combatCategoryRepository.findById(3L)).willReturn(Optional.of(combatCategoryFemale));
        given(formCategoryRepository.findById(1L)).willReturn(Optional.of(formCategory));
    }


    @Test
    public void test_tournament_composition() {
        Set<Participant> participants = new HashSet<>();
        participants.add(createParticipant(2L, "fernando","cambarieri"));
        participants.add(createParticipant(3L, "Juan","vizzioli"));
        participants.add(createParticipant(4L, "Gaston","donoban"));
        participants.add(createParticipant(5L, "Joaquin","donoban", combatCategory2, SexType.MALE));
        participants.add(createParticipant(6L, "ismael","nacion", combatCategory2, SexType.MALE));
        participants.add(createParticipant(7L, "Abril","Cambarieri", combatCategoryFemale, null,  SexType.FEMALE));
        participants.add(createParticipant(8L, "Anto","Cerutti", combatCategoryFemale, null, SexType.FEMALE));

        given(participantRepository.findAllParticipantByTournamentId(1L)).willReturn(participants);

        var result = dashboardServiceImp.getTournamentComposition(1L);

        Assertions.assertEquals(4, result.size());
    }

    @Test
    public void test_tournament_bracket() {
        Set<Participant> participants = new HashSet<>();
        participants.add(createParticipant(2L, "fernando","cambarieri"));
        participants.add(createParticipant(3L, "Juan","vizzioli"));
        participants.add(createParticipant(4L, "Gaston","donoban"));
        participants.add(createParticipant(5L, "Joaquin","donoban", combatCategory2, SexType.MALE));
        participants.add(createParticipant(6L, "ismael","nacion", combatCategory2, SexType.MALE));
        participants.add(createParticipant(7L, "Abril","Cambarieri", combatCategoryFemale, null,  SexType.FEMALE));
        participants.add(createParticipant(8L, "Anto","Cerutti", combatCategoryFemale, null, SexType.FEMALE));

        given(participantRepository.findAllParticipantByTournamentId(1L)).willReturn(participants);

        var result = dashboardServiceImp.getTournamentCompositionMap(1L);

        Map<CompositionKey, Competition> competitionList = new HashMap<>();

        result.forEach((compositionKey, participants1) -> {
            var builder = CompetitionFactory.INSTANCE.getBuilder(compositionKey.getBaseCategory().getFormat());
            competitionList.put(compositionKey, builder.createCompetition(tournament, compositionKey, participants1));
        });

        competitionList.values().forEach(competition -> {
            var response = CompetitionResponseBuilder.build(competition);
            System.out.println(TestHelper.asJsonString(response));
        });

        Assertions.assertEquals(4, competitionList.size());

        var competitionCombat = competitionList.values().stream().filter(competition -> competition.getCategory().equals(combatCategory)).findFirst().get();
        Assertions.assertNotNull(competitionCombat);
        BracketCompetition bracketCompetition = (BracketCompetition) competitionCombat;
        Assertions.assertEquals(2, bracketCompetition.getMatches().size());

        var competitionFormat = competitionList.values().stream().filter(competition -> competition.getCategory().equals(formCategory)).findFirst().get();
        Assertions.assertNotNull(competitionCombat);
        StageCompetition stageCompetition = (StageCompetition) competitionFormat;
        Assertions.assertEquals(1, stageCompetition.getStages().size());
        Assertions.assertEquals(5, stageCompetition.getStages().get(0).getParticipants().size());
    }

    @Test
    public void create_combat_and_format_competitions() {
        Set<Participant> participants = new HashSet<>();
        participants.add(createParticipant(2L, "fernando", "cambarieri"));
        participants.add(createParticipant(3L, "Juan", "vizzioli"));
        participants.add(createParticipant(4L, "Gaston", "donoban"));

        given(participantRepository.findAllParticipantByTournamentId(1L)).willReturn(participants);

        Query q = mock(Query.class);
        given(q.setParameter(anyString(), any())).willReturn(q);
        given(q.executeUpdate()).willReturn(1);
        given(entityManager.createQuery(anyString())).willReturn(q);

        dashboardServiceImp.createCompetitions(1L);

        verify(entityManager, times(2)).persist(any());
        verify(entityManager, times(1)).createQuery(anyString());
    }

    public Participant createParticipant(Long id, String name, String lastName) {
        return createParticipant(id, name, lastName, SexType.MALE);
    }

    public Participant createParticipant(Long id, String name, String lastName, SexType sex) {
        return createParticipant(id, name, lastName, combatCategory,sex);
    }

    public Participant createParticipant(Long id, String name, String lastName, CombatCategory cb, SexType sex) {
        return createParticipant(id, name, lastName, cb, formCategory, sex);
    }
    public Participant createParticipant(Long id, String name, String lastName, CombatCategory cb, FormCategory fc, SexType sex) {
        Participant p = new Participant();
        p.setId(id);
        p.setBeltCategory(beltCategory);
        p.setSex(sex);
        p.setTeam(participantTeam);
        p.setBirthDate(LocalDate.of(1990, 5, 30));
        p.setName(name);
        p.setLastName(lastName);
        p.setHeight(170);
        p.setWeight(83.0);
        p.setEmail("fcambarieri@gmail.com");
        p.setStatus(ParticipantStatus.VALIDATED);
        p.setTournament(tournament);

        Set<Modality> modalities = new HashSet<>();
        Modality m = new Modality();
        m.setParticipant(p);
        m.setType(ModalityType.COMBAT);
        m.setCategory(cb);
        modalities.add(m);

        if (fc != null ) {
            m = new Modality();
            m.setParticipant(p);
            m.setType(ModalityType.FORM);
            m.setCategory(fc);
            modalities.add(m);
        }

        p.setModalities(modalities);

        return p;
    }
}
