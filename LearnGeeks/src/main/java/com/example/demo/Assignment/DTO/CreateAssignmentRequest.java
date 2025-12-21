package com.example.demo.Assignment.DTO;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateAssignmentRequest(

        @NotBlank(message = "Assignment title is required")
        String title,

        String description,

        @NotNull(message = "Subject ID is required")
        Long subjectId,

        @NotNull(message = "Teacher ID is required")
        Long teacherId,

        @NotNull(message = "Deadline is required")
        LocalDateTime deadline
) {
}
