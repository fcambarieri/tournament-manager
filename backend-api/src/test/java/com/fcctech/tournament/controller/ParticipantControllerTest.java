package com.fcctech.tournament.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fcctech.tournament.TestDomainHelper;
import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.WithMockUser;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.ParticipantStatus;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.entity.*;
import com.fcctech.tournament.payload.request.ModalityRequest;
import com.fcctech.tournament.payload.request.ParticipantRequest;
import com.fcctech.tournament.payload.response.PageResponse;
import com.fcctech.tournament.payload.response.ParticipantResponse;
import com.fcctech.tournament.repository.ParticipantRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ParticipantControllerTest {
    @Autowired
    private MockMvc api;

    @Autowired
    private ParticipantRepository participantRepository;

    @Test
    @WithMockUser
    public void test_participant_registration() throws Exception {
        api.perform(post("/tournaments/1/teams/1/participants").content(TestHelper.asJsonString(defaultParticipantRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsStringIgnoringCase("id")));
    }

    @Test
    @WithMockUser
    public void test_participant_update() throws Exception {
        ParticipantRequest request = defaultParticipantRequest();
        request.setName("Ferdinando");
        api.perform(put("/tournaments/1/teams/1/participants/1").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    @WithMockUser
    public void test_get_participant() throws Exception {
        Participant p = createParticipant();

        var result = api.perform(get("/tournaments/1/teams/1/participants/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("id")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser
    public void test_get_participant_by_tournament_and_team() throws Exception {
        Participant p = createParticipant();
        var result = api.perform(get("/tournaments/1/teams/1/participants")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        List<ParticipantResponse> participants = TestHelper.mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ParticipantResponse>>() {});

        Assertions.assertFalse(participants.isEmpty());
    }

    @Test
    @WithMockUser
    public void test_get_all_participant_by_tournament() throws Exception {
        Participant p = createParticipant();

        var result = api.perform(get("/tournaments/1/participants")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        PageResponse<ParticipantResponse> participants = TestHelper.mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PageResponse<ParticipantResponse>>() {});

        Assertions.assertFalse(participants.getResult().isEmpty());
    }

    @Test
    @WithMockUser
    public void test_delete_participant_from_tournament_by_tournament_owner() throws Exception {
        Participant p = createParticipant();

        api.perform(delete("/tournaments/1/participants/".concat(p.getId().toString()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @WithMockUser(userId = 3)
    public void fail_delete_participant_from_tournament_forbidden() throws Exception {
        Participant p = createParticipant();

        api.perform(delete("/tournaments/1/participants/".concat(p.getId().toString()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message", Is.is("access denied")));
    }

    @Test
    @WithMockUser(userId = 2)
    public void delete_participant_from_tournament_by_subscriber() throws Exception {
        Participant p = createParticipant(participant -> {
            ParticipantTeam team = new ParticipantTeam();
            team.setId(2L);
            participant.setTeam(team);
        });

        api.perform(delete("/tournaments/1/participants/".concat(p.getId().toString()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private ParticipantRequest defaultParticipantRequest() {
        return ParticipantRequest.builder()
                .name("fernando")
                .lastName("cambarieri")
                .email("fcambarieri@gmail.com")
                .weight(85.5)
                .height(165)
                .sex(SexType.MALE)
                .beltCategoryId(1L)
                .dateBirth(LocalDate.of(1980, 12, 30))
                .modalities(List.of(ModalityRequest.builder()
                                .type(ModalityType.COMBAT)
                                .categoryId(1L)
                                .build(),
                        ModalityRequest.builder()
                                .type(ModalityType.FORM)
                                .categoryId(2L)
                                .build()))
                .build();
    }

    public Participant createParticipant() {
        return createParticipant(participant -> {});
    }
    public Participant createParticipant(Consumer<Participant> beforeSave) {
        Tournament t = new Tournament();
        t.setId(1L);
        BeltCategory b = new BeltCategory();
        b.setId(1L);
        CombatCategory w = new CombatCategory();
        w.setId(1L);
        FormCategory s = new FormCategory();
        s.setId(2L);
        ParticipantTeam pt = new ParticipantTeam();
        pt.setId(1L);

        Participant p = new Participant();
        p.setId(22L);
        p.setBeltCategory(b);
        p.setSex(SexType.MALE);
        p.setTeam(pt);
        p.setBirthDate(LocalDate.of(1990, 5, 30));
        p.setName("Fernando");
        p.setLastName("camba");
        p.setHeight(170);
        p.setWeight(83.0);
        p.setEmail("fcambarieri@gmail.com");
        p.setStatus(ParticipantStatus.VALIDATED);
        p.setTournament(t);

        Set<Modality> modalities = new HashSet<>();
        Modality m = new Modality();
        m.setParticipant(p);
        m.setType(ModalityType.COMBAT);
        m.setCategory(w);
        modalities.add(m);

        m = new Modality();
        m.setParticipant(p);
        m.setType(ModalityType.FORM);
        m.setCategory(s);

        modalities.add(m);

        p.setModalities(modalities);

        beforeSave.accept(p);

        return participantRepository.save(p);
    }
}
