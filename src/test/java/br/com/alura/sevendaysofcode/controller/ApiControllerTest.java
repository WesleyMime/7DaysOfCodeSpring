package br.com.alura.sevendaysofcode.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String RESULTS = """
            {"results":[{"id":""";
    private static final String ERROR_MESSAGE_NULL = """
            }],"errorMessage":null}""";
    private static final String NOT_FOUND = """
            {"results":[],"errorMessage":"Anime not found."}""";

    @Test
    void shouldReturnTop250Animes() throws Exception {
        mockMvc.perform(get("/api/"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(RESULTS)))
                .andExpect(content().string(Matchers.containsString(ERROR_MESSAGE_NULL)));
    }

    @Test
    void shouldReturnAnimeByTitle() throws Exception {
        mockMvc.perform(get("/api/attack"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(RESULTS)))
                .andExpect(content().string(Matchers.containsString(ERROR_MESSAGE_NULL)));
    }

    @Test
    void shouldReturnFavorites() throws Exception {
        mockMvc.perform(get("/api/favorites"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(ERROR_MESSAGE_NULL)));
    }

    @Test
    void shouldPostFavorite() throws Exception {
        mockMvc.perform(post("/api/favorites/tt13706018"))
                .andExpect(status().isCreated())
                .andExpect(content().string(Matchers.containsString(RESULTS)))
                .andExpect(content().string(Matchers.containsString(ERROR_MESSAGE_NULL)));
    }

    @Test
    void shouldNotPostFavorite() throws Exception {
        mockMvc.perform(post("/api/favorites/2313745685"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(Matchers.containsString(NOT_FOUND)));
    }

    @Test
    void shouldDeleteFavorite() throws Exception {
        mockMvc.perform(delete("/api/favorites/tt13706018"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(RESULTS)))
                .andExpect(content().string(Matchers.containsString(ERROR_MESSAGE_NULL)));
    }

    @Test
    void shouldNotDeleteFavorite() throws Exception {
        mockMvc.perform(delete("/api/favorites/2313745685"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(Matchers.containsString(NOT_FOUND)));
    }
}
