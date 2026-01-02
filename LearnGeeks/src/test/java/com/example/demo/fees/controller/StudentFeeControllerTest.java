package com.example.demo.fees.controller;

import com.example.demo.fees.dto.StudentFeeRequest;
import com.example.demo.fees.dto.StudentFeeResponse;
import com.example.demo.fees.service.StudentFeeService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentFeeController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentFeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentFeeService studentFeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetStudentFees() throws Exception {
        
        StudentFeeResponse response = new StudentFeeResponse(10L, 1L, 1L, 1000.0, 0.0, false);

        when(studentFeeService.getStudentFees(1L))
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/students/1/fees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].amountDue").value(1000.0));
    }

    @Test
    void shouldAssignFeeToStudent() throws Exception {
        // studentId, feeStructureId
        StudentFeeRequest request = new StudentFeeRequest(1L, 1L);

        doNothing().when(studentFeeService).assignFeeToStudent(any(StudentFeeRequest.class));

        mockMvc.perform(post("/api/students/1/fees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
