package com.example.demo.lms.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateProgressRequest(
        @NotNull(message = "Student ID is required")
        Long studentId,

        @NotNull(message = "Lesson Material ID is required")
        Long lessonMaterialId,

        @Min(0) @Max(100)
        double progress
) {
}
