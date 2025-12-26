package com.example.demo.onlineexam.service;

import com.example.demo.onlineexam.dto.CreateExamRequest;
import com.example.demo.onlineexam.dto.ExamResponse;

import java.util.List;

public interface ExamService {

    ExamResponse createExam(CreateExamRequest request);

    void publishExam(Long examId);

    List<ExamResponse> getPublishedExams();

    ExamResponse getExamById(Long examId);
}
