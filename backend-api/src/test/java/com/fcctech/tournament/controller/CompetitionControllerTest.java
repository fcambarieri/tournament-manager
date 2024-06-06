package com.fcctech.tournament.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.WithMockUser;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.ParticipantStatus;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.domain.competition.CompetitionBuilder;
import com.fcctech.tournament.domain.competition.CompetitionFactory;
import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.domain.competition.MatchStatus;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.payload.request.MatchRequest;
import com.fcctech.tournament.payload.response.competition.CompositionKey;
import com.fcctech.tournament.repository.CompetitionRepository;
import com.fcctech.tournament.repository.ParticipantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompetitionControllerTest {


    @Autowired
    private MockMvc api;

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private CompetitionRepository competitionRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private Tournament tournament;
    private BeltCategory beltCategory;
    private CombatCategory combatCategory;

    private ParticipantTeam participantTeam;


    @BeforeEach
    public void setup() {
        combatCategory = entityManager.find(CombatCategory.class, 1L);
        beltCategory = entityManager.find(BeltCategory.class, 1L);
        tournament = entityManager.find(Tournament.class, 1L);
        participantTeam = entityManager.find(ParticipantTeam.class, 1L);
    }

    @Test
    @Order(1)
    public void get_tournament_categories_composition() throws Exception {

        var result = api.perform(get("/tournaments/1/competitions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @Order(2)
    public void get_composition() throws Exception {
        Competition competition = createCompetition();
        var result = api.perform(get(String.format("/tournaments/1/competitions/%d", competition.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.tournament_id", Is.is(1)))
                .andExpect(jsonPath("$.belt.id", Is.is(1)))
                .andExpect(jsonPath("$.belt.name", Is.is("dan")))
                .andExpect(jsonPath("$.status", Is.is("READY")))
                .andExpect(jsonPath("$.matches", hasSize(2)))
                .andReturn()
                .getResponse()
                .getContentAsString()
        ;
        System.out.println(result);
    }

    @Test
    @WithMockUser
    public void update_match_competition() throws Exception {
        BracketCompetition competition = createCompetition();
        Optional<Match> match = competition.getMatches().stream().filter(match1 -> match1.getStatus().equals(MatchStatus.READY)).findFirst();
        Assertions.assertFalse(match.isEmpty());

        Optional<Match> matchNotReady = competition.getMatches().stream().filter(match1 -> match1.getStatus().equals(MatchStatus.WAITING_MATCH)).findFirst();
        Assertions.assertFalse(matchNotReady.isEmpty());

        MatchRequest request = MatchRequest.builder()
                .end(LocalTime.now())
                .start(LocalTime.now())
                .winner(match.get().leftParticipantId())
                .build();
        var result = api.perform(put(String.format("/tournaments/1/competitions/%d/matches/%d", competition.getId(), match.get().getId()))
                        .content(TestHelper.mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.next_match.id", Is.is(match.get().nextMatchId().intValue())))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println("PUT RESULT -> ".concat(result));
        Map<String, Map<String, Integer>> map = TestHelper.mapper.readValue(result, new TypeReference<Map<String, Map<String, Integer>>>() {
        });

        int nextMatch = map.get("next_match").get("id");

        result = api.perform(get(String.format("/tournaments/1/competitions/%d/matches/%d", competition.getId(), match.get().getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(String.format("GETTING MATCH UPDATED %d -> %s", match.get().getId(), result));

        result = api.perform(get(String.format("/tournaments/1/competitions/%d/matches/%d", competition.getId(), nextMatch))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(String.format("GETTING NEXT MATCH %d -> %s", nextMatch, result));


    }

    private List<Participant> participants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(createParticipant(90L, "fer","camba","ups1@gmail.com", combatCategory));
        participants.add(createParticipant(91L, "Ivan","Camacho","ups2@gmail.com", combatCategory));
        participants.add(createParticipant(92L, "Isma","Wicher","ups3@gmail.com", combatCategory));
        return participants;
    }

    private BracketCompetition createCompetition() {
        CompetitionBuilder<BracketCompetition> builder = CompetitionFactory.INSTANCE.getBuilder(CompetitionFormat.SINGLE_ELIMINATION);
        BracketCompetition competition = builder.createCompetition(tournament,
                CompositionKey.builder()
                        .baseCategory(combatCategory)
                        .sex(SexType.MALE)
                        .modality(ModalityType.COMBAT)
                        .belt(beltCategory)
                        .categoryId(combatCategory.getId())
                        .build(),
                participants());

        return competitionRepository.save(competition);
    }

    public Participant createParticipant(Long id, String name, String lastName, String email, CombatCategory cb) {
        Participant p = new Participant();
        p.setId(id);
        p.setBeltCategory(beltCategory);
        p.setSex(SexType.MALE);
        p.setTeam(participantTeam);
        p.setBirthDate(LocalDate.of(1990, 5, 30));
        p.setName(name);
        p.setLastName(lastName);
        p.setHeight(170);
        p.setWeight(83.0);
        p.setEmail(email);
        p.setStatus(ParticipantStatus.VALIDATED);
        p.setTournament(tournament);

        Set<Modality> modalities = new HashSet<>();
        Modality m = new Modality();
        m.setParticipant(p);
        m.setType(ModalityType.COMBAT);
        m.setCategory(cb);
        modalities.add(m);


        p.setModalities(modalities);

        return participantRepository.save(p);
    }
}