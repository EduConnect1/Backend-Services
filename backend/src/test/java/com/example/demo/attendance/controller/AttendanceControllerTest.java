package com.example.demo.attendance.controller;

import com.example.demo.attendance.dto.*;
import com.example.demo.attendance.model.AttendanceStatus;
import com.example.demo.attendance.service.AttendanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.demo.core.security.JwtAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

@WebMvcTest(AttendanceController.class)
@AutoConfigureMockMvc(addFilters = false)
class AttendanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttendanceService attendanceService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void shouldCreateSession() throws Exception {
        
        CreateAttendanceSessionRequest request = new CreateAttendanceSessionRequest(1L, 1L, 1L, LocalDate.now());
        
        AttendanceSessionResponse response = new AttendanceSessionResponse(10L, 1L, "Class A", 1L, "Math", 1L, "Teacher", LocalDate.now());

        when(attendanceService.createAttendanceSession(any(CreateAttendanceSessionRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/attendance/sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10));
    }

    @Test
    void shouldMarkAttendance() throws Exception {
        MarkAttendanceRequest request = new MarkAttendanceRequest(1L, AttendanceStatus.PRESENT, "Remark");
        List<MarkAttendanceRequest> requests = List.of(request);

        doNothing().when(attendanceService).markAttendance(eq(1L), anyList());

        mockMvc.perform(post("/api/attendance/sessions/1/mark")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requests)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAttendanceBySession() throws Exception {
        AttendanceRecordResponse record = new AttendanceRecordResponse(1L, "Student A", AttendanceStatus.PRESENT, "Remark");

        when(attendanceService.getAttendanceBySession(1L)).thenReturn(List.of(record));

        mockMvc.perform(get("/api/attendance/sessions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].studentName").value("Student A"));
    }

    @Test
    void shouldGetStudentSummary() throws Exception {
        StudentAttendanceSummaryResponse response = new StudentAttendanceSummaryResponse(1L, "Student A", 10, 0, 0, 100.0);

        when(attendanceService.getStudentAttendanceSummary(eq(1L), any(), any())).thenReturn(response);

        mockMvc.perform(get("/api/attendance/students/1/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.attendancePercentage").value(100.0));
    }

    @Test
    void shouldGetClassSummary() throws Exception {
        ClassAttendanceSummaryResponse response = new ClassAttendanceSummaryResponse(1L, "Class A", 20, 95.0);

        when(attendanceService.getClassAttendanceSummary(1L)).thenReturn(response);

        mockMvc.perform(get("/api/attendance/classes/1/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageAttendancePercentage").value(95.0));
    }
}
