package com.example.demo.onlineexam.dto;

public record CreateOptionRequest(
        Long questionId,
        String optionText,
        Boolean correct
) {}
