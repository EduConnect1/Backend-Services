package com.example.demo.LearningManagementSystem.DTO;

public record StudentCourseProgressResponse(
        Long studentId,
        String studentName,
        Long courseId,
        String courseTitle,
        double completionPercentage
) {
}
