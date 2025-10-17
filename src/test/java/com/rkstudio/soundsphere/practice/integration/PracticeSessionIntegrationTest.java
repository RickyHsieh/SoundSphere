package com.rkstudio.soundsphere.practice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkstudio.soundsphere.practice.entity.PracticeSession;
import com.rkstudio.soundsphere.practice.repository.PracticeSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@ActiveProfiles("test")
@Transactional
class PracticeSessionIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PracticeSessionRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        repository.deleteAll();
    }

    @Test
    void createAndRetrieveSession_ShouldWork() throws Exception {
        // Given
        PracticeSession session = new PracticeSession(
                "Integration Test Session",
                "Testing full flow",
                30,
                LocalDateTime.of(2024, 1, 15, 14, 0)
        );

        // When - Create session
        String response = mockMvc.perform(post("/api/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(session)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Integration Test Session"))
                .andExpect(jsonPath("$.notes").value("Testing full flow"))
                .andExpect(jsonPath("$.durationMinutes").value(30))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Parse the created session ID
        PracticeSession createdSession = objectMapper.readValue(response, PracticeSession.class);
        Long sessionId = createdSession.getId();

        // Verify it was saved to database
        assertThat(repository.findById(sessionId)).isPresent();

        // When - Retrieve session
        mockMvc.perform(get("/api/sessions/" + sessionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sessionId))
                .andExpect(jsonPath("$.title").value("Integration Test Session"))
                .andExpect(jsonPath("$.notes").value("Testing full flow"))
                .andExpect(jsonPath("$.durationMinutes").value(30));

        // When - Get all sessions
        mockMvc.perform(get("/api/sessions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(sessionId))
                .andExpect(jsonPath("$[0].title").value("Integration Test Session"));
    }

    @Test
    void updateSession_ShouldWork() throws Exception {
        // Given - Create a session first
        PracticeSession originalSession = new PracticeSession(
                "Original Title",
                "Original notes",
                45,
                LocalDateTime.now()
        );
        PracticeSession savedSession = repository.save(originalSession);
        Long sessionId = savedSession.getId();

        // Given - Updated session data
        PracticeSession updatedSession = new PracticeSession(
                "Updated Title",
                "Updated notes",
                60,
                LocalDateTime.now()
        );

        // When - Update session
        mockMvc.perform(put("/api/sessions/" + sessionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSession)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sessionId))
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.notes").value("Updated notes"))
                .andExpect(jsonPath("$.durationMinutes").value(60));

        // Verify in database
        PracticeSession dbSession = repository.findById(sessionId).orElseThrow();
        assertThat(dbSession.getTitle()).isEqualTo("Updated Title");
        assertThat(dbSession.getNotes()).isEqualTo("Updated notes");
        assertThat(dbSession.getDurationMinutes()).isEqualTo(60);
    }

    @Test
    void deleteSession_ShouldWork() throws Exception {
        // Given - Create a session first
        PracticeSession session = new PracticeSession(
                "To Be Deleted",
                "This will be deleted",
                15,
                LocalDateTime.now()
        );
        PracticeSession savedSession = repository.save(session);
        Long sessionId = savedSession.getId();

        // Verify it exists
        assertThat(repository.findById(sessionId)).isPresent();

        // When - Delete session
        mockMvc.perform(delete("/api/sessions/" + sessionId))
                .andExpect(status().isNoContent());

        // Verify it's deleted
        assertThat(repository.findById(sessionId)).isEmpty();
    }
}
