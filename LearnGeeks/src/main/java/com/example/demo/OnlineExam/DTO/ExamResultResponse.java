package com.example.demo.OnlineExam.DTO;

public record ExamResultResponse(

        Long studentId,
        Long examId,
        int score,
        boolean passed
) {
}
