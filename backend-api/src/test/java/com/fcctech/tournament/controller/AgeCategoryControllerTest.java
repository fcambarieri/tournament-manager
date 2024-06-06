package com.fcctech.tournament.controller;

import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.WithMockUser;
import com.fcctech.tournament.payload.request.AgeCategoryRequest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AgeCategoryControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    @WithMockUser
    public void create_age_category() throws Exception {
        AgeCategoryRequest request = AgeCategoryRequest.builder()
                .name("minis")
                .minAge(5)
                .maxAge(8)
                .build();

        api.perform(post("/tournaments/1/categories").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsStringIgnoringCase("id")));
    }

    @Test
    @WithMockUser
    public void update_age_category() throws Exception {
        AgeCategoryRequest request = AgeCategoryRequest.builder()
                .name("minis_2")
                .minAge(9)
                .maxAge(10)
                .build();

        api.perform(put("/tournaments/1/categories/301").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void get_age_category() throws Exception {
        api.perform(get("/tournaments/1/categories/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is("adults")))
                .andExpect(jsonPath("$.min_age", Is.is(30)))
                .andExpect(jsonPath("$.max_age", Is.is(100)))
        ;
    }

    @Test
    @WithMockUser
    public void get_all_age_category() throws Exception {
        var result = api.perform(get("/tournaments/4/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
        ;
        String response = result.getResponse().getContentAsString();
        System.out.println("ALL CATEGORIES -> " + response);
    }
}
