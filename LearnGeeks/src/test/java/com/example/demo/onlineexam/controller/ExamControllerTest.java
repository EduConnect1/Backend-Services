package com.example.demo.onlineexam.controller;

import com.example.demo.onlineexam.dto.*;
import com.example.demo.onlineexam.service.ExamAttemptService;
import com.example.demo.onlineexam.service.ExamService;
import com.example.demo.onlineexam.service.OptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExamController.class)
@AutoConfigureMockMvc(addFilters = false)
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @MockBean
    private OptionService optionService;

    @MockBean
    private ExamAttemptService examAttemptService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateExam() throws Exception {
        CreateExamRequest request = new CreateExamRequest("Midterm", 1L, 60, 100, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1));
        
        
        ExamResponse response = new ExamResponse(10L, "Midterm", 1L, 60, 100, LocalDateTime.now(), LocalDateTime.now(), true);

        when(examService.createExam(any(CreateExamRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/exams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.title").value("Midterm"));
    }

    @Test
    void shouldAddOption() throws Exception {
        CreateOptionRequest request = new CreateOptionRequest(1L, "Option A", true);

        doNothing().when(optionService).createOption(any(CreateOptionRequest.class));

        mockMvc.perform(post("/api/exams/questions/options")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldStartExam() throws Exception {
        StartExamRequest request = new StartExamRequest(1L, 2L);

        when(examAttemptService.startExam(any(StartExamRequest.class))).thenReturn(100L);

        mockMvc.perform(post("/api/exams/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    void shouldSubmitAnswer() throws Exception {
        
        SubmitAnswerRequest request = new SubmitAnswerRequest(100L, 1L, "Option A");

        doNothing().when(examAttemptService).submitAnswer(any(SubmitAnswerRequest.class));

        mockMvc.perform(post("/api/exams/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSubmitExam() throws Exception {
        
        ExamResultResponse response = new ExamResultResponse(1L, 2L, 85, true);

        when(examAttemptService.submitExam(100L)).thenReturn(response);

        mockMvc.perform(post("/api/exams/submit/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(85));
    }

    @Test
    void shouldGetExam() throws Exception {
        
        ExamResponse response = new ExamResponse(1L, "Final", 1L, 60, 100, LocalDateTime.now(), LocalDateTime.now(), true);

        when(examService.getExamById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/exams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Final"));
    }
}
