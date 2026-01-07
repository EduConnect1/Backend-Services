package com.example.demo.assignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubmitAssignmentRequest(

        @NotNull(message = "Assignment ID is required")
        Long assignmentId,

        @NotBlank(message = "File URL is required")
        String fileUrl
) {
}

