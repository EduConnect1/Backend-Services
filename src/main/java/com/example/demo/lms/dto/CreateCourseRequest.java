package com.example.demo.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCourseRequest(
        @NotBlank(message = "Course title is required")
        String title,

        String description,

        @NotNull(message = "Teacher ID is required")
        Long teacherId
) {
}
