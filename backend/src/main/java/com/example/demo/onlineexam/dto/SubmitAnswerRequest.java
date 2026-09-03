package com.example.demo.onlineexam.dto;

import jakarta.validation.constraints.NotNull;

public record SubmitAnswerRequest(

        @NotNull
        Long attemptId,

        @NotNull
        Long questionId,

        String answer
) {
}
