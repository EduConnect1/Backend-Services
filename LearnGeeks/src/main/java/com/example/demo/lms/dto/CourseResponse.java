package com.example.demo.lms.dto;
import java.time.LocalDateTime;
import java.util.List;

public record CourseResponse(
        Long id,
        String title,
        String description,
        Long teacherId,
        String teacherName,
        LocalDateTime createdAt,
        List<ModuleResponse> modules
) {
}
