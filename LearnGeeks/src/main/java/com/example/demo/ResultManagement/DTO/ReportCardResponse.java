package com.example.demo.ResultManagement.DTO;

import java.util.List;

public record ReportCardResponse(
        Long studentId,
        String term,
        int year,
        List<ExamResultResponse> results
) {}
