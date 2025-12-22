package com.example.demo.ResultManagement.service;

import com.example.demo.ResultManagement.DTO.ExamResultRequest;
import com.example.demo.ResultManagement.DTO.ExamResultResponse;


import java.util.List;

public interface ExamResultService {

    ExamResultResponse addOrUpdateResult(ExamResultRequest request);

    List<ExamResultResponse> getResultsByStudent(Long studentId);

    String calculateGrade(Integer marks); // Helper method for grading
}
