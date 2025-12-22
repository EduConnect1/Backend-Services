package com.example.demo.OnlineExam.DTO;

import java.time.LocalDateTime;

public record ExamResponse(

        Long id,
        String title,
        Long courseId,
        int durationMinutes,
        int totalMarks,
        LocalDateTime startTime,
        LocalDateTime endTime,
        boolean published
) {
}
