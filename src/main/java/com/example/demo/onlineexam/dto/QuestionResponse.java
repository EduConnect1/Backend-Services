package com.example.demo.onlineexam.dto;

import com.example.demo.onlineexam.model.QuestionType;

import java.util.List;

public record QuestionResponse(

        Long id,
        String questionText,
        QuestionType type,
        int marks,
        List<OptionResponse> options
) {
}
