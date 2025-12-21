package com.example.demo.Assignment.DTO;

public record StudentAssignmentSummaryResponse(

        Long studentId,
        String studentName,
        long totalAssignments,
        long submittedCount,
        long lateCount,
        double submissionRate
) {
}
