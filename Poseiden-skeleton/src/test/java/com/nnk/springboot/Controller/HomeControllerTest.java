package com.nnk.springboot.Controller;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing security enforcement of the main scenarios under spring security for certain endpoints.
 *
 * User mocking and passing real users to test for roles user and admin.
 *
 * Test checks whether anonymous, user and admin roles had the right level of access to resources.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("test")
    @DisplayName("GET - Checks registered user can see home")
    public void checksHomeAccess() throws Exception {
        //ARRANGE
        String url = "/";
        //ACT
        this.mockMvc
                .perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @DisplayName("GET - Checks anonymous can see login page")
    public void AnonynomusUser() throws Exception {
        //ARRANGE
        String url = "/login";
        //ACT
        this.mockMvc
                .perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @DisplayName("GET - Checks anonymous can see Home page")
    public void AnonynomusUserHomeAcess() throws Exception {
        //ARRANGE
        String url = "/";
        //ACT
        this.mockMvc
                .perform(get(url))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails("test")
    @DisplayName("GET - Checks anonymous user has forbidden access to see admin content")
    public void ForbiddenAccess() throws Exception {
        //ARRANGE
        String url = "/admin/home";
        //ACT
        this.mockMvc
                .perform(get(url))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    @DisplayName("GET - Checks admin user can see its content")
    public void adminHasAccess() throws Exception {
        //ARRANGE
        String url = "/admin/home";
        //ACT
        this.mockMvc
                .perform(get(url))
                .andExpect(status().is3xxRedirection());
    }
}
