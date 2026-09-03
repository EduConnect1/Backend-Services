package com.example.demo.analytics.dto;

public record CourseEngagementResponse(

        Long courseId,
        String courseTitle,
        double completionRate,
        long activeStudents
) {}
