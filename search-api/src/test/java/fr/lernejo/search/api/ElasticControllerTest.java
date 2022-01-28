package fr.lernejo.search.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class ElasticControllerTest {

    @Test
    void getGames(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/games?query=developer:\"Epic Games\""))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
