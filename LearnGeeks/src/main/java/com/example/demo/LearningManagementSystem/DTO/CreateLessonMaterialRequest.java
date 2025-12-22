package com.example.demo.LearningManagementSystem.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLessonMaterialRequest(
        @NotBlank(message = "Material title is required")
        String title,

        @NotBlank(message = "Content URL is required")
        String contentUrl,

        @NotNull(message = "Module ID is required")
        Long moduleId
) {
}
