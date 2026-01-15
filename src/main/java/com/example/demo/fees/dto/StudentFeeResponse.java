package com.example.demo.fees.dto;

public record StudentFeeResponse(
        Long id,
        Long studentId,
        Long feeStructureId,
        Double amountDue,
        Double amountPaid,
        Boolean fullyPaid
) {}

