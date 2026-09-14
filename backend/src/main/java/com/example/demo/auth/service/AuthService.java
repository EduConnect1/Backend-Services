package com.example.demo.auth.service;

import com.example.demo.auth.dto.AuthResponse;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.auth.entity.Role;
import com.example.demo.auth.entity.User;
import com.example.demo.auth.entity.UserStatus;
import com.example.demo.auth.enums.RoleEnum;
import com.example.demo.auth.repository.RoleRepository;
import com.example.demo.auth.repository.UserRepository;
import com.example.demo.core.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        RoleEnum targetRoleEnum = RoleEnum.ROLE_STUDENT;
        if (request.getRole() != null) {
            try {
                String roleStr = request.getRole().toUpperCase();
                if (!roleStr.startsWith("ROLE_")) {
                    roleStr = "ROLE_" + roleStr;
                }
                targetRoleEnum = RoleEnum.valueOf(roleStr);
            } catch (Exception ignored) {
            }
        }

        final RoleEnum roleToFind = targetRoleEnum;
        Role role = roleRepository.findByRole(roleToFind)
                .orElseGet(() -> roleRepository.save(Role.builder().role(roleToFind).build()));

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : "")
                .roles(roles)
                .verified(true)
                .status(UserStatus.ACTIVE)
                .build();

        user = userRepository.save(user);

        String token = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Set<String> roleNames = user.getRoles().stream()
                .map(r -> r.getRole().name())
                .collect(Collectors.toSet());

        return new AuthResponse(
                token,
                refreshToken,
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                roleNames
        );
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Set<String> roleNames = user.getRoles().stream()
                .map(r -> r.getRole().name())
                .collect(Collectors.toSet());

        return new AuthResponse(
                token,
                refreshToken,
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                roleNames
        );
    }
}
