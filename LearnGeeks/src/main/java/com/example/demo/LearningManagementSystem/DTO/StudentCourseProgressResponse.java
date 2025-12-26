package com.example.demo.learningmanagementsystem.dto;

public record StudentCourseProgressResponse(
        Long studentId,
        String studentName,
        Long courseId,
        String courseTitle,
        double completionPercentage
) {
}
