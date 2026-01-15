package com.example.demo.onlineexam.dto;

public record ExamResultResponse(

        Long studentId,
        Long examId,
        int score,
        boolean passed
) {
}
