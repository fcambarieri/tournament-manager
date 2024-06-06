package com.fcctech.tournament.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.WithMockUser;
import com.fcctech.tournament.domain.competition.CompetitionFormat;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.domain.SexType;
import com.fcctech.tournament.payload.request.FormCategoryRequest;
import com.fcctech.tournament.payload.response.FormCategoryResponse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FormCategoryControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    @WithMockUser
    public void create_form_category() throws Exception {
        FormCategoryRequest request = FormCategoryRequest.builder()
                .name("None sense")
                .minAge(7)
                .maxAge(9)
                .maxParticipants(1)
                .type(ModalityType.FORM)
                .sex(SexType.MALE)
                .format(CompetitionFormat.STAGES)
                .build();

        api.perform(post("/tournaments/1/form_categories").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsStringIgnoringCase("id")));
    }

    @Test
    @WithMockUser
    public void update_form_category() throws Exception {
        FormCategoryRequest request = FormCategoryRequest.builder()
                .name("None sense")
                .minAge(10)
                .maxAge(50)
                .maxParticipants(1)
                .type(ModalityType.FORM)
                .sex(SexType.MALE)
                .format(CompetitionFormat.STAGES)
                .build();

        api.perform(put("/tournaments/1/form_categories/2").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    @WithMockUser
    public void get_form_category() throws Exception {
        var result = api.perform(get("/tournaments/1/form_categories/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(2)))
                .andExpect(jsonPath("$.sex", Is.is("MALE")))
                .andExpect(jsonPath("$.max_participant", Is.is(1)))
                .andExpect(jsonPath("$.type", Is.is("FORM")))
                .andReturn()
                ;
        System.out.println("CAT=> " + result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser
    public void get_all_combat_category() throws Exception {
        var result = api.perform(get("/tournaments/1/form_categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                ;
        List<FormCategoryResponse> categories = TestHelper.mapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<FormCategoryResponse>>() {});

        Assertions.assertFalse(categories.isEmpty());
    }
}
