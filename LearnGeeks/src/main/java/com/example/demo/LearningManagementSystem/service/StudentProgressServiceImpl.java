package com.example.demo.learningmanagementsystem.service;

import com.example.demo.learningmanagementsystem.dto.StudentCourseProgressResponse;

import com.example.demo.learningmanagementsystem.model.StudentCourseProgress;

import com.example.demo.learningmanagementsystem.repository.StudentCourseProgressRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentProgressServiceImpl implements StudentProgressService {

    private final StudentCourseProgressRepository progressRepository;
    

    @Override
    public StudentCourseProgressResponse getStudentProgress(Long studentId, Long courseId) {
        StudentCourseProgress progress = progressRepository
                .findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        return new StudentCourseProgressResponse(
                studentId,
                progress.getStudent().getFullName(),
                courseId,
                progress.getCourse().getTitle(),
                progress.getCompletionPercentage()
        );
    }

    @Override
    public void updateProgress(Long studentId, Long lessonMaterialId) {
        
    }
}
