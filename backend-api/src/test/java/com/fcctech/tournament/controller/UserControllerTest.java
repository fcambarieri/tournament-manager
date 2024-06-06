package com.fcctech.tournament.controller;

import com.fcctech.tournament.TestHelper;
import com.fcctech.tournament.WithMockUser;
import com.fcctech.tournament.payload.request.AccountRequest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void anyoneCanViewPublicEndpoint() throws Exception {
        api.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("OK")));
    }

    @Test
    void notLoggedIn_ShowReturnException() throws Exception {
        api.perform(get("/user/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void LoggedIn_ShowReturnUser() throws Exception {
        api.perform(get("/user/me"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(userId = 2)
    void create_user_account() throws Exception {
        AccountRequest request = AccountRequest.builder()
                .country("ARG")
                .phoneNumber("542235756757")
                .associationName("Escuela Andina")
                .build();

        api.perform(post("/user/account").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsStringIgnoringCase("id")));
    }

    @Test
    @WithMockUser
    void fail_create_user_account() throws Exception {
        AccountRequest request = AccountRequest.builder()
                .country("ARG")
                .phoneNumber("542235756757")
                .associationName("Escuela Andina")
                .build();

        api.perform(post("/user/account").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsStringIgnoringCase("account already created")));
    }

    @Test
    @WithMockUser
    void update_account() throws Exception {
        AccountRequest request = AccountRequest.builder()
                .country("ARG")
                .phoneNumber("888888")
                .associationName("Times Query Andina")
                .build();

        api.perform(put("/user/account/88").content(TestHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void get_account() throws Exception {
        api.perform(get("/user/account/88")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", Is.is("test@test.com")))
                .andExpect(jsonPath("$.country", Is.is("ARG")))
                .andExpect(jsonPath("$.association_name").exists())
                .andExpect(jsonPath("$.phone_number").exists())
        ;
    }

}
