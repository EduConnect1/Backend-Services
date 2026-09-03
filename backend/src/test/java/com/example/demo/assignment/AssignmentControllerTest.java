package com.example.demo.assignment;

import com.example.demo.assignment.dto.*;
import com.example.demo.assignment.controller.AssignmentController;
import com.example.demo.assignment.service.AssignmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.core.security.JwtAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

@WebMvcTest(AssignmentController.class)
@AutoConfigureMockMvc(addFilters = false)
class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentService assignmentService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    void shouldCreateAssignment() throws Exception {

        CreateAssignmentRequest request = new CreateAssignmentRequest(
                "Math Homework", "Desc", 1L, 1L, LocalDateTime.now().plusDays(7)
        );

        AssignmentResponse response = new AssignmentResponse(
                10L, "Math Homework", "Desc", 1L, "Math", 1L, "Mr. Smith", LocalDateTime.now(), LocalDateTime.now()
        );

        when(assignmentService.createAssignment(any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/assignments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.title").value("Math Homework"));
    }

    
    @Test
    void shouldSubmitAssignment() throws Exception {

        SubmitAssignmentRequest request = new SubmitAssignmentRequest(5L, "http://file.url");

        
        doNothing().when(assignmentService).submitAssignment(eq(1L), any());

        mockMvc.perform(post("/api/assignments/submit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    
    @Test
    void shouldGradeAssignment() throws Exception {

        GradeAssignmentRequest request = new GradeAssignmentRequest(85.0, "Good work");

        doNothing().when(assignmentService)
                .gradeAssignment(eq(1L), any());

        mockMvc.perform(put("/api/assignments/grade/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    
    @Test
    void shouldGetSubmissionsByAssignment() throws Exception {

        when(assignmentService.getSubmissionsByAssignment(1L))
                .thenReturn(List.of(
                        new AssignmentSubmissionResponse(1L, "Student 1", "url", null, null, null, null),
                        new AssignmentSubmissionResponse(2L, "Student 2", "url", null, null, null, null)
                ));

        mockMvc.perform(get("/api/assignments/1/submissions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    
    @Test
    void shouldGetAssignmentsBySubject() throws Exception {

        when(assignmentService.getAssignmentsBySubject(1L))
                .thenReturn(List.of(
                        new AssignmentResponse(1L, "Title", "Desc", 1L, "Sub", 1L, "Teacher", LocalDateTime.now(), LocalDateTime.now())
                ));

        mockMvc.perform(get("/api/assignments/subject/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void shouldGetStudentAssignmentSummary() throws Exception {

        when(assignmentService.getStudentAssignmentSummary(1L))
                .thenReturn(new StudentAssignmentSummaryResponse(1L, "Student", 10, 5, 2, 50.0));

        mockMvc.perform(get("/api/assignments/students/1/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(1L));
    }
}
