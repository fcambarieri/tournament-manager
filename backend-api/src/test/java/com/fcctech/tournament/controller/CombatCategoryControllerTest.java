package com.fcctech.tournament.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.WithMockUser;
import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.payload.request.CombatCategoryRequest;
import com.fcctech.tournament.payload.response.CombatCategoryResponse;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CombatCategoryControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    @WithMockUser
    public void create_combat_category() throws Exception {
        CombatCategoryRequest request = CombatCategoryRequest.builder()
                .categoryId(1L)
                .sex(SexType.MALE)
                .name("one to one")
                .maxParticipants(1)
                .type(ModalityType.COMBAT)
                .minWeight(70)
                .maxWeight(79)
                .format(CompetitionFormat.SINGLE_ELIMINATION)
                .build();

        api.perform(post("/tournaments/1/combat_categories").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsStringIgnoringCase("id")));
    }

    @Test
    @WithMockUser
    public void update_combat_category() throws Exception {
        CombatCategoryRequest request = CombatCategoryRequest.builder()
                .categoryId(1L)
                .sex(SexType.MALE)
                .name("one to many")
                .maxParticipants(1)
                .type(ModalityType.COMBAT)
                .minWeight(80)
                .maxWeight(87)
                .format(CompetitionFormat.STAGES)
                .build();

        api.perform(put("/tournaments/1/combat_categories/1").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    @WithMockUser
    public void get_combat_category() throws Exception {
        var result = api.perform(get("/tournaments/1/combat_categories/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.sex", Is.is("MALE")))
                .andExpect(jsonPath("$.max_participant", Is.is(1)))
                .andExpect(jsonPath("$.type", Is.is("COMBAT")))
                .andReturn()
                ;
        System.out.println("CAT=> " + result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser
    public void get_all_combat_category() throws Exception {
        var result = api.perform(get("/tournaments/1/combat_categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                ;
        List<CombatCategoryResponse> categories = TestHelper.mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<CombatCategoryResponse>>() {
        });

        Assertions.assertFalse(categories.isEmpty());
    }
}
