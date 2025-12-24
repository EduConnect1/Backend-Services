package com.example.demo.OnlineExam.DTO;

public record CreateOptionRequest(
        Long questionId,
        String optionText,
        Boolean correct
) {}
