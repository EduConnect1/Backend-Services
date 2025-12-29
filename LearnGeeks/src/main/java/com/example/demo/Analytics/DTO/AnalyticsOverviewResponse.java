package com.example.demo.Analytics.DTO;

public record AnalyticsOverviewResponse(
        

        long totalStudents,
        double averageAttendanceRate,
        double assignmentCompletionRate,
        double courseCompletionRate
) {}
