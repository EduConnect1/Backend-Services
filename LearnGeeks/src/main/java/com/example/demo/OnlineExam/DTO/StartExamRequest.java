package com.example.demo.onlineexam.dto;

import jakarta.validation.constraints.NotNull;

public record StartExamRequest(

        @NotNull
        Long studentId,

        @NotNull
        Long examId
) {
}

