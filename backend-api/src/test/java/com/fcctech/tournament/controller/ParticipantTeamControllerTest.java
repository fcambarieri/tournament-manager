package com.fcctech.tournament.controller;

import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.WithMockUser;
import com.fcctech.tournament.payload.request.AgeCategoryRequest;
import com.fcctech.tournament.payload.request.ParticipantTeamRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ParticipantTeamControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    @WithMockUser(userId = 3)
    public void create_participant_team() throws Exception {

        api.perform(post("/tournaments/1/subscribe_me")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
