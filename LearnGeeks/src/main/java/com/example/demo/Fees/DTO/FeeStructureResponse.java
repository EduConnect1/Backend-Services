package com.example.demo.Fees.DTO;

public record FeeStructureResponse(
        Long id,
        Long classId,
        String description,
        Double amount,
        int year
) {}
