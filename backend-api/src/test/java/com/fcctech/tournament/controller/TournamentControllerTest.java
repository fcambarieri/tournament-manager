package com.fcctech.tournament.controller;


import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.WithMockUser;
import com.fcctech.tournament.payload.request.TournamentRequest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TournamentControllerTest {

    @Autowired
    private MockMvc api;
    @Test
    void notLoggedIn_ShowReturnException() throws Exception {
        api.perform(post("/tournaments").content(asDefaultTournamentJsonString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void createTournamentOK() throws Exception {
        api.perform(post("/tournaments").content(asDefaultTournamentJsonString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsStringIgnoringCase("id")));
    }

    @Test
    @WithMockUser
    void updateTournament() throws Exception {
        api.perform(put("/tournaments/1").content(asDefaultTournamentJsonString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(204));
    }

    @Test
    @WithMockUser
    void updateTournament_NotFound() throws Exception {
        api.perform(put("/tournaments/0").content(asDefaultTournamentJsonString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    @WithMockUser
    void updateTournament_access_denied() throws Exception {
        api.perform(put("/tournaments/2").content(asDefaultTournamentJsonString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @WithMockUser
    void updateTournament_bad_request_name_null() throws Exception {
        api.perform(put("/tournaments/2").content("""
                                {
                                    "name":"",
                                    "date_started": "2024-05-24",
                                    "date_end": "2024-05-25",
                                    "status": "open"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void get_tournament_unauthorized() throws Exception {
        api.perform(get("/tournaments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void get_tournament_not_found() throws Exception {
        api.perform(get("/tournaments/0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", Is.is("tournament not found")))
                .andExpect(jsonPath("$.status_code", Is.is(404)))
        ;
    }

    @Test
    @WithMockUser
    void get_tournament_ok() throws Exception {
        api.perform(get("/tournaments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is("PatagoniaOpen")))
                .andExpect(jsonPath("$.description", Is.is(nullValue())))
                .andExpect(jsonPath("$.date_started", containsStringIgnoringCase("2024-05-15")))
                .andExpect(jsonPath("$.date_end", containsStringIgnoringCase("2024-05-16T03")))
                .andExpect(jsonPath("$.status", Is.is("OPEN")))
        ;
    }

    @Test
    @WithMockUser
    void get_all_tournament_pagination() throws Exception {
        api.perform(get("/tournaments?size=10&page=0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paging.size", Is.is(10) ))
                .andExpect(jsonPath("$.paging.page", Is.is(0) ))
                .andExpect(jsonPath("$.paging.total", Is.is(4) ))
                .andExpect(jsonPath("$.result", Is.isA(List.class)))

        ;
    }

    private String asDefaultTournamentJsonString() {
        return TestHelper.asJsonString(TournamentRequest.builder()
                .name("Patagonia Open Sesson 2")
                .dateStarted(TestHelper.toDate(2025, 5, 15))
                .dateEnd(TestHelper.toDate(2025, 5, 16))
                .type("taekwondo")
                .build());
    }

}
