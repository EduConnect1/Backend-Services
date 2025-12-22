package com.example.demo.ResultManagement.DTO;

public record ExamResultRequest(
        Long studentId,
        Long subjectId,
        Integer marks
) {}
