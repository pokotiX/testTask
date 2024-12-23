package com.example.log;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Unit test for the LogController
@WebMvcTest
public class LogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogApi() throws Exception {
        // Simulate a POST request to /api/log
        mockMvc.perform(post("/api/log")
                        .content("10") // Input payload: number as a string
                        .contentType(MediaType.APPLICATION_JSON)) // Set content type to JSON
                .andExpect(status().isOk()); // Expect the result to be 200 OK
    }
}