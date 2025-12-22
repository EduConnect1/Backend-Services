package com.example.demo.OnlineExam.DTO;

import com.example.demo.OnlineExam.Model.QuestionType;

import java.util.List;

public record QuestionResponse(

        Long id,
        String questionText,
        QuestionType type,
        int marks,
        List<OptionResponse> options
) {
}
