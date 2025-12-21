package com.example.demo.LearningManagementSystem.service;

import com.example.demo.LearningManagementSystem.DTO.StudentCourseProgressResponse;


import com.example.demo.LearningManagementSystem.Model.StudentCourseProgress;

import com.example.demo.LearningManagementSystem.repository.StudentCourseProgressRepository;

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
        // Business logic placeholder
        // Update completed lessons & recalculate percentage
    }
}
