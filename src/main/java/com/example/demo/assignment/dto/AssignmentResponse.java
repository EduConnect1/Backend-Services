package com.example.demo.assignment.dto;

import java.time.LocalDateTime;

public record AssignmentResponse(

        Long id,
        String title,
        String description,
        Long subjectId,
        String subjectName,
        Long teacherId,
        String teacherName,
        LocalDateTime deadline,
        LocalDateTime createdAt
) {
}

