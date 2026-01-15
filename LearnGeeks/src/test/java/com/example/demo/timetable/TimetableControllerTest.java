package com.example.demo.timetable;

import com.example.demo.timetable.dto.TimetableSessionRequest;
import com.example.demo.timetable.dto.TimetableSessionResponse;
import com.example.demo.timetable.service.TimetableService;
import com.example.demo.timetable.controller.TimetableController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.demo.core.security.JwtAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

@WebMvcTest(TimetableController.class)
@AutoConfigureMockMvc(addFilters = false) 
class TimetableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimetableService timetableService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    
    @Test
    void shouldCreateTimetableSession() throws Exception {

        TimetableSessionRequest request = new TimetableSessionRequest(2L, 1L, 3L, null, null, null);

        TimetableSessionResponse response = new TimetableSessionResponse(10L, null, null, null, null, null, null);

        when(timetableService.createSession(any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/timetables")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10));
    }

    
    @Test
    void shouldUpdateTimetableSession() throws Exception {

        TimetableSessionRequest request = new TimetableSessionRequest(3L, 2L, null, null, null, null);

        TimetableSessionResponse response = new TimetableSessionResponse(1L, null, null, null, null, null, null);

        when(timetableService.updateSession(eq(1L), any()))
                .thenReturn(response);

        mockMvc.perform(put("/api/timetables/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    
    @Test
    void shouldDeleteTimetableSession() throws Exception {

        doNothing().when(timetableService).deleteSession(1L);

        mockMvc.perform(delete("/api/timetables/1"))
                .andExpect(status().isOk());
    }

    
    @Test
    void shouldGetTimetableByTeacher() throws Exception {

        when(timetableService.getTeacherTimetable(1L))
                .thenReturn(List.of(
                        new TimetableSessionResponse(1L, null, null, null, null, null, null),
                        new TimetableSessionResponse(2L, null, null, null, null, null, null)
                ));

        mockMvc.perform(get("/api/timetables/teacher/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    
    @Test
    void shouldGetTimetableByClass() throws Exception {

        when(timetableService.getClassTimetable(1L))
                .thenReturn(List.of(
                        new TimetableSessionResponse(5L, null, null, null, null, null, null)
                ));

        mockMvc.perform(get("/api/timetables/class/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }
}
