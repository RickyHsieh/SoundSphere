package com.rkstudio.soundsphere.auth.service;

import com.rkstudio.soundsphere.auth.dto.AuthResponse;
import com.rkstudio.soundsphere.auth.dto.LoginRequest;
import com.rkstudio.soundsphere.auth.dto.RegisterRequest;
import com.rkstudio.soundsphere.auth.entity.Role;
import com.rkstudio.soundsphere.auth.entity.User;
import com.rkstudio.soundsphere.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    /**
     * Register a new user
     */
    public AuthResponse register(RegisterRequest request) {
        // Create new user with USER role by default
        User user = userService.createUser(
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                Role.USER
        );

        // Generate JWT token
        String token = jwtUtil.generateToken(user);

        // Return response with token and user info
        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    /**
     * Authenticate user and generate token
     */
    public AuthResponse login(LoginRequest request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Get authenticated user
        User user = (User) authentication.getPrincipal();

        // Generate JWT token
        String token = jwtUtil.generateToken(user);

        // Return response with token and user info
        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
