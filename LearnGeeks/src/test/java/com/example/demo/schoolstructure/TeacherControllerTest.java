package com.example.demo.schoolstructure;

package com.example.demo.schoolstructure.controller;

import com.example.demo.schoolstructure.dto.teacherdto.AssignSubjectRequest;
import com.example.demo.schoolstructure.dto.teacherdto.CreateTeacherRequest;
import com.example.demo.schoolstructure.dto.teacherdto.TeacherResponse;
import com.example.demo.schoolstructure.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
@AutoConfigureMockMvc(addFilters = false)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private ObjectMapper objectMapper;

    // POST /api/teachers
    @Test
    void shouldCreateTeacher() throws Exception {

        CreateTeacherRequest request = CreateTeacherRequest.builder()
                .userId(1L)
                .build();

        TeacherResponse response = TeacherResponse.builder()
                .id(10L)
                .userId(1L)
                .build();

        when(teacherService.createTeacher(any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10));
    }

    // POST /api/teachers/{teacherId}/subjects
    @Test
    void shouldAssignSubjectsToTeacher() throws Exception {

        AssignSubjectRequest request = AssignSubjectRequest.builder()
                .subjectIds(List.of(1L, 2L))
                .build();

        doNothing().when(teacherService).assignSubjects(eq(10L), any());

        mockMvc.perform(post("/api/teachers/10/subjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
