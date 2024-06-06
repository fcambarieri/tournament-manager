package com.fcctech.tournament.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.WithMockUser;
import com.fcctech.tournament.payload.request.BeltRequest;
import com.fcctech.tournament.payload.response.BeltCategory;
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
public class BeltControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    @WithMockUser
    public void create_belt_category() throws Exception {
        BeltRequest request = BeltRequest.builder().name("amarilo_vede").build();

        api.perform(post("/tournaments/1/belts").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsStringIgnoringCase("id")));
    }

    @Test
    @WithMockUser
    public void update_belt_category() throws Exception {
        BeltRequest request = BeltRequest.builder().name("amarilo_vede").build();

        api.perform(put("/tournaments/1/belts/1").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void get_belt_category() throws Exception {
        api.perform(get("/tournaments/1/belts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.isA(Integer.class)))
                .andExpect(jsonPath("$.name", Is.isA(String.class)))
        ;
    }

    @Test
    @WithMockUser
    public void get_all_belt_categories() throws Exception {
        var result = api.perform(get("/tournaments/1/belts")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
        ;
        List<BeltCategory> belts = TestHelper.mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<BeltCategory>>() {});
        Assertions.assertFalse(belts.isEmpty());
    }
}
