package com.rkstudio.soundsphere.practice.service;

import com.rkstudio.soundsphere.practice.entity.PracticeSession;
import com.rkstudio.soundsphere.practice.repository.PracticeSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PracticeSessionService {

    private final PracticeSessionRepository repository;

    public PracticeSessionService(PracticeSessionRepository repository) {
        this.repository = repository;
    }

    public List<PracticeSession> findAll() {
        return repository.findAll();
    }

    public Optional<PracticeSession> findById(Long id) {
        return repository.findById(id);
    }

    public PracticeSession save(PracticeSession session) {
        return repository.save(session);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
