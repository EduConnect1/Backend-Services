package com.example.demo.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Analytics.DTO.*;
import com.example.demo.Analytics.Service.AnalyticsService;
import com.example.demo.Analytics.Controller.AnalyticsController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AnalyticsController.class)
@AutoConfigureMockMvc(addFilters = false)
class AnalyticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnalyticsService analyticsService;

    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    void shouldGetAnalyticsOverview() throws Exception {

        AnalyticsOverviewResponse response = new AnalyticsOverviewResponse(200L, 85.0, 90.0, 95.0);

        when(analyticsService.getOverview())
                .thenReturn(response);

        mockMvc.perform(get("/api/analytics/overview"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalStudents").value(200));
    }

    
    @Test
    void shouldGetCourseEngagement() throws Exception {

        when(analyticsService.getCourseEngagement())
                .thenReturn(List.of(
                        new CourseEngagementResponse(1L, "Course 1", 75.0, 10L),
                        new CourseEngagementResponse(2L, "Course 2", 60.0, 5L)
                ));

        mockMvc.perform(get("/api/analytics/course-engagement"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].completionRate").value(75.0));
    }

    
    @Test
    void shouldGetAttendanceHeatmap() throws Exception {

        LocalDate now = LocalDate.now();
        when(analyticsService.getAttendanceHeatmap(anyLong()))
                .thenReturn(List.of(
                        new AttendanceHeatMapResponse(now, 80.0)
                ));

        mockMvc.perform(get("/api/analytics/attendance/heatmap")
                        .param("classId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].attendancePercentage").value(80.0));
    }

    
    @Test
    void shouldGetAtRiskStudents() throws Exception {

        when(analyticsService.getAtRiskStudents(anyDouble()))
                .thenReturn(List.of(
                        new AtRiskStudentResponse(1L, "Student 1", 50.0, 40.0)
                ));

        mockMvc.perform(get("/api/analytics/at-risk-students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].studentName").value("Student 1"));
    }
}
