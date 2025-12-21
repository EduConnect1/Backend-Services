package com.example.demo.LearningManagementSystem.service;


import com.example.demo.LearningManagementSystem.DTO.StudentCourseProgressResponse;

public interface StudentProgressService {

    StudentCourseProgressResponse getStudentProgress(Long studentId, Long courseId);

    void updateProgress(Long studentId, Long lessonMaterialId);
}
