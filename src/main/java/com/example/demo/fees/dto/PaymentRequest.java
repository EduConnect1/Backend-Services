package com.example.demo.fees.dto;

import java.time.LocalDateTime;

public record PaymentRequest(
        Long studentId,
        Double amount,
        LocalDateTime paymentDate,
        String method
) {}
