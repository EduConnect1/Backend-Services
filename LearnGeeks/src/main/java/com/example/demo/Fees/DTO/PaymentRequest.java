package com.example.demo.Fees.DTO;

import java.time.LocalDateTime;

public record PaymentRequest(
        Long studentId,
        Double amount,
        LocalDateTime paymentDate,
        String method
) {}
