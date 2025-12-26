package com.example.demo.learningmanagementsystem.service;

import com.example.demo.learningmanagementsystem.dto.StudentCourseProgressResponse;

public interface StudentProgressService {

    StudentCourseProgressResponse getStudentProgress(Long studentId, Long courseId);

    void updateProgress(Long studentId, Long lessonMaterialId);
}
