package com.example.demo.fees.dto;

public record StudentFeeRequest(
        Long studentId,
        Long feeStructureId
) {}
