package com.example.demo.OnlineExam.service;

import com.example.demo.OnlineExam.DTO.CreateExamRequest;
import com.example.demo.OnlineExam.DTO.ExamResponse;

import java.util.List;

public interface ExamService {

    ExamResponse createExam(CreateExamRequest request);

    void publishExam(Long examId);

    List<ExamResponse> getPublishedExams();

    ExamResponse getExamById(Long examId);
}
