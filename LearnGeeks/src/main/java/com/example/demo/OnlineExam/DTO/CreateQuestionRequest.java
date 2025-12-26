package com.example.demo.onlineexam.dto;

import com.example.demo.onlineexam.model.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateQuestionRequest(

        @NotNull
        Long examId,

        @NotBlank
        String questionText,

        QuestionType type,

        int marks
) {
}
