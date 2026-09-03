package com.example.demo.lms.dto;

public record StudentCourseProgressResponse(
        Long studentId,
        String studentName,
        Long courseId,
        String courseTitle,
        double completionPercentage
) {
}
