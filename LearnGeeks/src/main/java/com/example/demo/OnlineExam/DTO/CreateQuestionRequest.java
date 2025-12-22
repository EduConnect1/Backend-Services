package com.example.demo.OnlineExam.DTO;

import com.example.demo.OnlineExam.Model.QuestionType;
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
