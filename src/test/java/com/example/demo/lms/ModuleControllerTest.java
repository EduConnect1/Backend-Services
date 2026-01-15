package com.example.demo.lms;

import com.example.demo.lms.dto.CreateModuleRequest;
import com.example.demo.lms.dto.ModuleResponse;
import com.example.demo.lms.service.ModuleService;
import com.example.demo.lms.controller.ModuleController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.demo.core.security.JwtAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

@WebMvcTest(ModuleController.class)
@AutoConfigureMockMvc(addFilters = false)
class ModuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModuleService moduleService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    
    @Test
    void shouldCreateModule() throws Exception {

        CreateModuleRequest request = new CreateModuleRequest(
                "Algebra", "Algebra Description", 1L
        );

        ModuleResponse response = new ModuleResponse(
                10L, "Algebra", "Algebra Description", Collections.emptyList()
        );

        when(moduleService.createModule(any(CreateModuleRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/lms/modules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.title").value("Algebra"));
    }

    
    @Test
    void shouldGetModulesByCourse() throws Exception {

        ModuleResponse response = new ModuleResponse(
                10L, "Algebra", "Algebra Description", Collections.emptyList()
        );

        when(moduleService.getModulesByCourse(1L))
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/lms/modules/course/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }
}
