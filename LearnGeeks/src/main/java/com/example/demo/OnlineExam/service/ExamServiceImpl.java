package com.example.demo.OnlineExam.service;
import com.example.demo.LearningManagementSystem.repository.*;
import com.example.demo.OnlineExam.repository.*;

import com.example.demo.OnlineExam.service.ExamServiceImpl;
import com.example.demo.OnlineExam.DTO.*;
import com.example.demo.LearningManagementSystem.Model.*;
import com.example.demo.OnlineExam.Model.*;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    @Override
    public ExamResponse createExam(CreateExamRequest request) {

        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Exam exam = Exam.builder()
                .title(request.title())
                .course(course)
                .durationMinutes(request.durationMinutes())
                .totalMarks(request.totalMarks())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .published(false)
                .build();

        examRepository.save(exam);

        return mapToResponse(exam);
    }

    @Override
    public void publishExam(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        exam.setPublished(true);
        examRepository.save(exam);
    }

    @Override
    public List<ExamResponse> getPublishedExams() {
        return examRepository.findByPublishedTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ExamResponse getExamById(Long examId) {
        return examRepository.findById(examId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }

    private ExamResponse mapToResponse(Exam exam) {
        return new ExamResponse(
                exam.getId(),
                exam.getTitle(),
                exam.getCourse().getId(),
                exam.getDurationMinutes(),
                exam.getTotalMarks(),
                exam.getStartTime(),
                exam.getEndTime(),
                exam.isPublished()
        );
    }
}
