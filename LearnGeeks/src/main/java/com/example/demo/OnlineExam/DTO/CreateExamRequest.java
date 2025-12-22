package com.example.demo.OnlineExam.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateExamRequest(

        @NotBlank
        String title,

        @NotNull
        Long courseId,

        int durationMinutes,

        int totalMarks,

        LocalDateTime startTime,

        LocalDateTime endTime
) {
}
