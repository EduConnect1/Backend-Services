package com.example.demo.Fees.DTO;

public record StudentFeeResponse(
        Long id,
        Long studentId,
        Long feeStructureId,
        Double amountDue,
        Double amountPaid,
        Boolean fullyPaid
) {}

