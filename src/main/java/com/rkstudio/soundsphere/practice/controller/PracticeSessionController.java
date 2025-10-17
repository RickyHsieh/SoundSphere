package com.rkstudio.soundsphere.practice.controller;

import com.rkstudio.soundsphere.practice.entity.PracticeSession;
import com.rkstudio.soundsphere.practice.service.PracticeSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
public class PracticeSessionController {

    private final PracticeSessionService service;

    public PracticeSessionController(PracticeSessionService service) {
        this.service = service;
    }

    @GetMapping
    public List<PracticeSession> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PracticeSession> getById(@PathVariable Long id) {
        Optional<PracticeSession> session = service.findById(id);
        return session.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PracticeSession> create(@RequestBody PracticeSession session) {
        PracticeSession savedSession = service.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PracticeSession> update(@PathVariable Long id, @RequestBody PracticeSession session) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        session.setId(id);
        PracticeSession updatedSession = service.save(session);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
