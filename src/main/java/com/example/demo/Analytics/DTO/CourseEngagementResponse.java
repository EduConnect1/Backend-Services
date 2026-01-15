package com.example.demo.Analytics.DTO;

public record CourseEngagementResponse(

        Long courseId,
        String courseTitle,
        double completionRate,
        long activeStudents
) {}
