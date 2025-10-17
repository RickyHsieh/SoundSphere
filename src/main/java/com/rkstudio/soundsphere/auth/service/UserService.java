package com.rkstudio.soundsphere.auth.service;

import com.rkstudio.soundsphere.auth.entity.Role;
import com.rkstudio.soundsphere.auth.entity.User;
import com.rkstudio.soundsphere.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Find user by username
     * @throws UsernameNotFoundException if user not found
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    /**
     * Find user by email
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Check if username already exists
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Check if email already exists
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Create a new user
     * Password will be encoded automatically
     */
    @Transactional
    public User createUser(String username, String email, String rawPassword, Role role) {
        // Validate username and email uniqueness
        if (existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }
        if (existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }

        // Create and save user
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .role(role)
                .build();

        return userRepository.save(user);
    }

    /**
     * Update user password
     */
    @Transactional
    public void updatePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
