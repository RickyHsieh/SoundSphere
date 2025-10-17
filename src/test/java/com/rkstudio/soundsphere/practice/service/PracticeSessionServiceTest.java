package com.rkstudio.soundsphere.practice.service;

import com.rkstudio.soundsphere.practice.entity.PracticeSession;
import com.rkstudio.soundsphere.practice.repository.PracticeSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PracticeSessionServiceTest {

    @Mock
    private PracticeSessionRepository repository;

    @InjectMocks
    private PracticeSessionService service;

    private PracticeSession testSession;

    @BeforeEach
    void setUp() {
        testSession = new PracticeSession(
                "Guitar Practice",
                "Pentatonic scale practice",
                45,
                LocalDateTime.now()
        );
        testSession.setId(1L);
    }

    @Test
    void findAll_ShouldReturnAllSessions() {
        // Given
        List<PracticeSession> sessions = Arrays.asList(testSession);
        when(repository.findAll()).thenReturn(sessions);

        // When
        List<PracticeSession> result = service.findAll();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(testSession);
        verify(repository).findAll();
    }

    @Test
    void findById_WhenSessionExists_ShouldReturnSession() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(testSession));

        // When
        Optional<PracticeSession> result = service.findById(1L);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testSession);
        verify(repository).findById(1L);
    }

    @Test
    void findById_WhenSessionNotExists_ShouldReturnEmpty() {
        // Given
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<PracticeSession> result = service.findById(999L);

        // Then
        assertThat(result).isEmpty();
        verify(repository).findById(999L);
    }

    @Test
    void save_ShouldReturnSavedSession() {
        // Given
        when(repository.save(any(PracticeSession.class))).thenReturn(testSession);

        // When
        PracticeSession result = service.save(testSession);

        // Then
        assertThat(result).isEqualTo(testSession);
        verify(repository).save(testSession);
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        // When
        service.deleteById(1L);

        // Then
        verify(repository).deleteById(1L);
    }

    @Test
    void existsById_WhenSessionExists_ShouldReturnTrue() {
        // Given
        when(repository.existsById(1L)).thenReturn(true);

        // When
        boolean result = service.existsById(1L);

        // Then
        assertThat(result).isTrue();
        verify(repository).existsById(1L);
    }

    @Test
    void existsById_WhenSessionNotExists_ShouldReturnFalse() {
        // Given
        when(repository.existsById(999L)).thenReturn(false);

        // When
        boolean result = service.existsById(999L);

        // Then
        assertThat(result).isFalse();
        verify(repository).existsById(999L);
    }
}
