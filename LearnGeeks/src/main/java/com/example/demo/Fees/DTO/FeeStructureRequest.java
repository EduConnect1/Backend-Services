package com.example.demo.fees.dto;

public record FeeStructureRequest(
        Long classId,
        String description,
        Double amount,
        int year
) {}

