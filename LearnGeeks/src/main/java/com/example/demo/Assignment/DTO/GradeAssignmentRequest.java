package com.example.demo.Assignment.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GradeAssignmentRequest(

        @NotNull(message = "Marks are required")
        @Min(0)
        @Max(100)
        Double marks,

        String feedback
) {
}
