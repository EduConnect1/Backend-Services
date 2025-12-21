package com.example.demo.LearningManagementSystem.DTO;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateModuleRequest(
        @NotBlank(message = "Module title is required")
        String title,

        String description,

        @NotNull(message = "Course ID is required")
        Long courseId
) {
}
