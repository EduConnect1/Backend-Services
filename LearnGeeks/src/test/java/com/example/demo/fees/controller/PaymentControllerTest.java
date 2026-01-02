package com.example.demo.fees.controller;

import com.example.demo.fees.dto.PaymentRequest;
import com.example.demo.fees.dto.PaymentResponse;
import com.example.demo.fees.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatePayment() throws Exception {
        // studentId, amount, paymentDate, method
        PaymentRequest request = new PaymentRequest(1L, 1200.0, LocalDateTime.now(), "Credit Card");

        doNothing().when(paymentService).recordPayment(any(PaymentRequest.class));

        mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetPaymentsByStudent() throws Exception {
        // id, studentId, amount, paymentDate, method
        PaymentResponse response = new PaymentResponse(1L, 1L, 1200.0, LocalDateTime.now(), "Credit Card");

        when(paymentService.getPaymentsForStudent(1L))
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/payments/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].amount").value(1200.0));
    }
}
