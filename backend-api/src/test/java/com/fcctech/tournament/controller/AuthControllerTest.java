package com.fcctech.tournament.controller;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTest {


    @Autowired
    private MockMvc api;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {
        String encode = passwordEncoder.encode("maluca.2015");
        System.out.println(encode);
    }

    @Test
    void registration() throws Exception {
        api.perform(post("/auth/registration").content("""
                        {
                            "email": "maluca@gmail.com",
                            "password": "maluca.2015",
                            "user_name": "maluca_4_ever"
                        }
                        """).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsStringIgnoringCase("id")));
    }

    @Test
    void registration_fail_email_already_in_used() throws Exception {
        api.perform(post("/auth/registration").content("""
                        {
                            "email": "mighty_thor@test.com",
                            "password": "maluca.2015",
                            "user_name": "nothing"
                        }
                        """).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Is.is("Email is already exists")));
    }

    @Test
    void registration_fail_username_already_in_used() throws Exception {
        api.perform(post("/auth/registration").content("""
                        {
                            "email": "juana@test.com",
                            "password": "thor",
                            "user_name": "thor"
                        }
                        """).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Username is already exists")));
    }

    @Test
    public void attempt_login_ok() throws Exception {
        api.perform(post("/auth/login").content("""
                        {
                            "email": "mighty_thor@test.com",
                            "password": "maluca.2015"
                        }
                        """).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("token")));
    }

    @Test
    public void attempt_login_user_fail() throws Exception {
        api.perform(post("/auth/login").content("""
                        {
                            "email": "thor@test.com",
                            "password": "maluca.2015"
                        }
                        """).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsStringIgnoringCase("message")));
    }

    @Test
    public void attempt_login_password_fail() throws Exception {
        api.perform(post("/auth/login").content("""
                        {
                            "email": "mighty_thor@test.com",
                            "password": "maluca"
                        }
                        """).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsStringIgnoringCase("message")));
    }
}
