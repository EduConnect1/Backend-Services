package com.example.demo.Analytics.DTO;

public record AtRiskStudentResponse(

        Long studentId,
        String studentName,
        double attendanceRate,
        double courseCompletionRate
) {}
