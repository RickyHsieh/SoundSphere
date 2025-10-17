package com.rkstudio.soundsphere.practice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "practice_sessions")
public class PracticeSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String notes;

    @Column(name = "duration_minutes")
    private int durationMinutes;

    @Column(name = "practiced_at")
    private LocalDateTime practicedAt;

    public PracticeSession() {}

    public PracticeSession(String title, String notes, int durationMinutes, LocalDateTime practicedAt) {
        this.title = title;
        this.notes = notes;
        this.durationMinutes = durationMinutes;
        this.practicedAt = practicedAt;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime getPracticedAt() {
        return practicedAt;
    }

    public void setPracticedAt(LocalDateTime practicedAt) {
        this.practicedAt = practicedAt;
    }

    @Override
    public String toString() {
        return "PracticeSession{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", notes='" + notes + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", practicedAt=" + practicedAt +
                '}';
    }
}
