package com.example.demo.ResultManagement.DTO;

public record ExamResultResponse(
        Long id,
        Long studentId,
        Long subjectId,
        Integer marks,
        String grade
) {}
