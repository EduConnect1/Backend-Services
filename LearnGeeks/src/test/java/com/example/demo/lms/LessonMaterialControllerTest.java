package com.example.demo.lms;

import com.example.demo.lms.dto.CreateLessonMaterialRequest;
import com.example.demo.lms.dto.LessonMaterialResponse;
import com.example.demo.lms.service.LessonMaterialService;
import com.example.demo.lms.controller.LessonMaterialController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LessonMaterialController.class)
@AutoConfigureMockMvc(addFilters = false)
class LessonMaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LessonMaterialService lessonMaterialService;

    @Autowired
    private ObjectMapper objectMapper;

    // =========================
    // POST /api/lms/materials
    // =========================
    @Test
    void shouldCreateLessonMaterial() throws Exception {

        CreateLessonMaterialRequest request = new CreateLessonMaterialRequest(
                "Lecture 1", "http://content.url", 1L
        );

        LessonMaterialResponse response = new LessonMaterialResponse(
                10L, "Lecture 1", "http://content.url"
        );

        when(lessonMaterialService.createLessonMaterial(any(CreateLessonMaterialRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/lms/materials")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.title").value("Lecture 1"));
    }

    // =========================
    // GET /api/lms/materials/module/{moduleId}
    // =========================
    @Test
    void shouldGetMaterialsByModule() throws Exception {

        LessonMaterialResponse response = new LessonMaterialResponse(
                10L, "Lecture 1", "http://content.url"
        );

        when(lessonMaterialService.getMaterialsByModule(1L))
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/lms/materials/module/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }
}
