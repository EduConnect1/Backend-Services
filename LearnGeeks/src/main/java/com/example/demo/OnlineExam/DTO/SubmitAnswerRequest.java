package com.example.demo.OnlineExam.DTO;

import jakarta.validation.constraints.NotNull;

public record SubmitAnswerRequest(

        @NotNull
        Long attemptId,

        @NotNull
        Long questionId,

        String answer
) {
}
