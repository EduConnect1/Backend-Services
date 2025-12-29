package com.example.demo.assignment.dto;

public record StudentAssignmentSummaryResponse(

        Long studentId,
        String studentName,
        long totalAssignments,
        long submittedCount,
        long lateCount,
        double submissionRate
) {
}
