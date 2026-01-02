package com.example.demo.lms;

import com.example.demo.lms.dto.StudentCourseProgressResponse;
import com.example.demo.lms.dto.UpdateProgressRequest;
import com.example.demo.lms.service.StudentProgressService;
import com.example.demo.lms.controller.StudentProgressController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentProgressController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentProgressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentProgressService studentProgressService;

    @Autowired
    private ObjectMapper objectMapper;

    // =========================
    // POST /api/lms/progress/update
    // =========================
    @Test
    void shouldUpdateProgress() throws Exception {

        UpdateProgressRequest request = new UpdateProgressRequest(1L, 2L, 50.0);

        StudentCourseProgressResponse response = new StudentCourseProgressResponse(
                1L, "Student Name", 1L, "Course Title", 50.0
        );

        when(studentProgressService.updateProgress(any(UpdateProgressRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/lms/progress/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completionPercentage").value(50.0));
    }

    // =========================
    // GET /api/lms/progress/student/{studentId}/course/{courseId}
    // =========================
    @Test
    void shouldGetStudentProgress() throws Exception {

        StudentCourseProgressResponse response = new StudentCourseProgressResponse(
                1L, "Student Name", 1L, "Course Title", 75.0
        );

        when(studentProgressService.getProgress(1L, 1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/lms/progress/student/1/course/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completionPercentage").value(75.0));
    }
}
