package com.example.demo.Fees.DTO;

public record FeeStructureRequest(
        Long classId,
        String description,
        Double amount,
        int year
) {}

