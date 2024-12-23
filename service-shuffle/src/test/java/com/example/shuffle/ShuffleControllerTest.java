package com.example.shuffle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ShuffleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private void performShuffleRequestAndVerify(int input) throws Exception {
        mockMvc.perform(post("/api/shuffle")
                        .content(String.valueOf(input)) // Convert input to JSON
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(input)))
                .andExpect(jsonPath("$[0]").isNumber())
                .andExpect(jsonPath("$[1]").isNumber());
    }

    @Test
    public void testShuffleApi() throws Exception {
        performShuffleRequestAndVerify(10);
        performShuffleRequestAndVerify(5);
    }
}