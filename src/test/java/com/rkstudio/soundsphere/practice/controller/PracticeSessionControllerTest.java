package com.rkstudio.soundsphere.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkstudio.soundsphere.practice.entity.PracticeSession;
import com.rkstudio.soundsphere.practice.service.PracticeSessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PracticeSessionController.class)
class PracticeSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PracticeSessionService service;

    @Autowired
    private ObjectMapper objectMapper;

    private PracticeSession testSession;

    @BeforeEach
    void setUp() {
        testSession = new PracticeSession(
                "Guitar Practice",
                "Pentatonic scale practice",
                45,
                LocalDateTime.of(2024, 1, 15, 9, 0)
        );
        testSession.setId(1L);
    }

    @Test
    void getAll_ShouldReturnAllSessions() throws Exception {
        // Given
        List<PracticeSession> sessions = Arrays.asList(testSession);
        when(service.findAll()).thenReturn(sessions);

        // When & Then
        mockMvc.perform(get("/api/sessions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Guitar Practice"))
                .andExpect(jsonPath("$[0].notes").value("Pentatonic scale practice"))
                .andExpect(jsonPath("$[0].durationMinutes").value(45));
    }

    @Test
    void getById_WhenSessionExists_ShouldReturnSession() throws Exception {
        // Given
        when(service.findById(1L)).thenReturn(Optional.of(testSession));

        // When & Then
        mockMvc.perform(get("/api/sessions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Guitar Practice"))
                .andExpect(jsonPath("$.notes").value("Pentatonic scale practice"))
                .andExpect(jsonPath("$.durationMinutes").value(45));
    }

    @Test
    void getById_WhenSessionNotExists_ShouldReturnNotFound() throws Exception {
        // Given
        when(service.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/sessions/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_ShouldReturnCreatedSession() throws Exception {
        // Given
        PracticeSession newSession = new PracticeSession(
                "Piano Practice",
                "Chopin Nocturne",
                60,
                LocalDateTime.of(2024, 1, 16, 10, 0)
        );
        when(service.save(any(PracticeSession.class))).thenReturn(newSession);

        // When & Then
        mockMvc.perform(post("/api/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSession)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Piano Practice"))
                .andExpect(jsonPath("$.notes").value("Chopin Nocturne"))
                .andExpect(jsonPath("$.durationMinutes").value(60));
    }

    @Test
    void update_WhenSessionExists_ShouldReturnUpdatedSession() throws Exception {
        // Given
        PracticeSession updatedSession = new PracticeSession(
                "Updated Guitar Practice",
                "Updated notes",
                90,
                LocalDateTime.of(2024, 1, 15, 9, 0)
        );
        updatedSession.setId(1L);
        
        when(service.existsById(1L)).thenReturn(true);
        when(service.save(any(PracticeSession.class))).thenReturn(updatedSession);

        // When & Then
        mockMvc.perform(put("/api/sessions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSession)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Guitar Practice"))
                .andExpect(jsonPath("$.notes").value("Updated notes"))
                .andExpect(jsonPath("$.durationMinutes").value(90));
    }

    @Test
    void update_WhenSessionNotExists_ShouldReturnNotFound() throws Exception {
        // Given
        when(service.existsById(999L)).thenReturn(false);

        // When & Then
        mockMvc.perform(put("/api/sessions/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSession)))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_WhenSessionExists_ShouldReturnNoContent() throws Exception {
        // Given
        when(service.existsById(1L)).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/api/sessions/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_WhenSessionNotExists_ShouldReturnNotFound() throws Exception {
        // Given
        when(service.existsById(999L)).thenReturn(false);

        // When & Then
        mockMvc.perform(delete("/api/sessions/999"))
                .andExpect(status().isNotFound());
    }
}
