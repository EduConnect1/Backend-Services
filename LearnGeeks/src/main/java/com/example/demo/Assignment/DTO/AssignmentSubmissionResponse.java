package com.example.demo.assignment.dto;
import com.example.demo.assignment.model.AssignmentStatus;
import java.time.LocalDateTime;

public record AssignmentSubmissionResponse(

        Long studentId,
        String studentName,
        String fileUrl,
        AssignmentStatus status,
        Double marks,
        String feedback,
        LocalDateTime submittedAt
) {
}
