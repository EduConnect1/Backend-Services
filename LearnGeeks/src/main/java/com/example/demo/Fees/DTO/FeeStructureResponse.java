package com.example.demo.fees.dto;

public record FeeStructureResponse(
        Long id,
        Long classId,
        String description,
        Double amount,
        int year
) {}
