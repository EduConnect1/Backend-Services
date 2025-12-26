package com.example.demo.analytics.dto;

public record AtRiskStudentResponse(

        Long studentId,
        String studentName,
        double attendanceRate,
        double courseCompletionRate
) {}
