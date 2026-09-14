package com.example.demo.auth;

import com.example.demo.auth.controller.AuthController;
import com.example.demo.auth.dto.AuthResponse;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.auth.service.AuthService;
import com.example.demo.auth.repository.UserRepository;
import com.example.demo.core.security.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterUser() throws Exception {
        RegisterRequest request = new RegisterRequest(
                "john.doe@example.com",
                "password123",
                "John",
                "Doe",
                "1234567890",
                "ROLE_STUDENT"
        );

        AuthResponse response = new AuthResponse(
                "mock-jwt-token",
                "mock-refresh-token",
                1L,
                "john.doe@example.com",
                "John",
                "Doe",
                Set.of("ROLE_STUDENT")
        );

        when(authService.register(any(RegisterRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock-jwt-token"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void shouldLoginUser() throws Exception {
        LoginRequest request = new LoginRequest(
                "john.doe@example.com",
                "password123"
        );

        AuthResponse response = new AuthResponse(
                "mock-jwt-token",
                "mock-refresh-token",
                1L,
                "john.doe@example.com",
                "John",
                "Doe",
                Set.of("ROLE_STUDENT")
        );

        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock-jwt-token"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }
}
