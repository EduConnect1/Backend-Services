package com.example.demo.lms.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateProgressRequest(
        @NotNull(message = "Student ID is required")
        Long studentId,

        @NotNull(message = "Module ID is required")
        Long moduleId,

        @Min(0) @Max(100)
        double progress
) {
}
