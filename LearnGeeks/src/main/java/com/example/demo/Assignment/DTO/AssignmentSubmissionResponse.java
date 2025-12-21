package com.example.demo.Assignment.DTO;
import com.example.demo.Assignment.Model.AssignmentStatus;
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
