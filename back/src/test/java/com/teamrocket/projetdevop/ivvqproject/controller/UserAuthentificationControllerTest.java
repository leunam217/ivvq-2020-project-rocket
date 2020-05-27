package com.teamrocket.projetdevop.ivvqproject.controller;

import com.teamrocket.projetdevop.ivvqproject.utils.AbstractRestControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class UserAuthentificationControllerTest extends AbstractRestControllerTest {


    @BeforeEach
    public void setUp() {
        SecurityContextHolder.clearContext();
    }


    @Test
    public void unsuccessful_authentication_with_wrong_password() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\", \"password\": \"wrong\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(not(containsString("token"))));
    }

    @Test
    public void unsuccessful_authentication_with_not_existing_user() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\", \"password\": \"not_existing\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(not(containsString("token"))));
    }

    @Test
    public void unsuccessful_authentication_with_disabled() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"disabled\", \"password\": \"password\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(not(containsString("token"))));
    }


    @Test
    public void should_not_authorized_get_userCart_Without_Token() throws Exception {
        getMockMvc().perform(get("/cart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }



}
