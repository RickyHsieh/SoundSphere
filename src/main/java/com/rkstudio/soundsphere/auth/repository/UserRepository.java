package com.rkstudio.soundsphere.auth.repository;

import com.rkstudio.soundsphere.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by username
     * Used for authentication
     */
    Optional<User> findByUsername(String username);

    /**
     * Find user by email
     * Used for registration validation
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if username already exists
     */
    boolean existsByUsername(String username);

    /**
     * Check if email already exists
     */
    boolean existsByEmail(String email);
}
