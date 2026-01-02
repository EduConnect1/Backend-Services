package com.example.demo.lms;

import com.example.demo.lms.controller.CourseController;
import com.example.demo.lms.dto.CourseResponse;
import com.example.demo.lms.dto.CreateCourseRequest;
import com.example.demo.lms.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllCourses() throws Exception {

        CourseResponse courseResponse = new CourseResponse(
                1L, "Math", "Math Course", 1L, "Teacher Name", LocalDateTime.now(), Collections.emptyList()
        );

        when(courseService.getAllCourses())
                .thenReturn(List.of(courseResponse));

        mockMvc.perform(get("/api/lms/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void shouldCreateCourse() throws Exception {

        CreateCourseRequest request = new CreateCourseRequest(
                "Physics", "Physics Course", 1L
        );

        CourseResponse response = new CourseResponse(
                2L, "Physics", "Physics Course", 1L, "Teacher Name", LocalDateTime.now(), Collections.emptyList()
        );

        when(courseService.createCourse(any(CreateCourseRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/lms/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.title").value("Physics"));
    }

    @Test
    void shouldGetCourseById() throws Exception {

        CourseResponse courseResponse = new CourseResponse(
                1L, "Math", "Math Course", 1L, "Teacher Name", LocalDateTime.now(), Collections.emptyList()
        );

        when(courseService.getCourseById(1L))
                .thenReturn(courseResponse);

        mockMvc.perform(get("/api/lms/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Math"));
    }

    @Test
    void shouldGetCoursesByTeacher() throws Exception {

        CourseResponse courseResponse = new CourseResponse(
                1L, "Math", "Math Course", 1L, "Teacher Name", LocalDateTime.now(), Collections.emptyList()
        );

        when(courseService.getCoursesByTeacher(1L))
                .thenReturn(List.of(courseResponse));

        mockMvc.perform(get("/api/lms/courses/teacher/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }
}
