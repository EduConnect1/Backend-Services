package com.example.demo.Fees.DTO;


import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long studentId,
        Double amount,
        LocalDateTime paymentDate,
        String method
) {}

