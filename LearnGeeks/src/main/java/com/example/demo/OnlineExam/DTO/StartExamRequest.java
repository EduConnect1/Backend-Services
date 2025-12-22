package com.example.demo.OnlineExam.DTO;

import jakarta.validation.constraints.NotNull;

public record StartExamRequest(

        @NotNull
        Long studentId,

        @NotNull
        Long examId
) {
}

