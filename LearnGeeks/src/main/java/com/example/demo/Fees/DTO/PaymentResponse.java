package com.example.demo.fees.dto;


import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long studentId,
        Double amount,
        LocalDateTime paymentDate,
        String method
) {}

