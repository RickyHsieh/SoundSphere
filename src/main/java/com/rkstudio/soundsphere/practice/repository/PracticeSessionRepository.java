package com.rkstudio.soundsphere.practice.repository;

import com.rkstudio.soundsphere.practice.entity.PracticeSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeSessionRepository extends JpaRepository<PracticeSession, Long> {
}
