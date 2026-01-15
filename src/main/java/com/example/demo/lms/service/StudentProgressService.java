package com.example.demo.lms.service;

import com.example.demo.lms.dto.StudentCourseProgressResponse;

public interface StudentProgressService {

    StudentCourseProgressResponse getStudentProgress(Long studentId, Long courseId);

    void updateProgress(Long studentId, Long lessonMaterialId);
}
