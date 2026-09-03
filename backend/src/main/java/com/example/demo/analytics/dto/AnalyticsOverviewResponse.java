package com.example.demo.analytics.dto;

public record AnalyticsOverviewResponse(
        

        long totalStudents,
        double averageAttendanceRate,
        double assignmentCompletionRate,
        double courseCompletionRate
) {}
