package com.example.demo.LearningManagementSystem.controller;

import com.school.automation.lms.dto.StudentCourseProgressResponse;
import com.school.automation.lms.service.StudentProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lms/progress")
@RequiredArgsConstructor
public class StudentProgressController {

    private final StudentProgressService studentProgressService;

    @GetMapping("/student/{studentId}/course/{courseId}")
    public StudentCourseProgressResponse getStudentProgress(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        return studentProgressService.getStudentProgress(studentId, courseId);
    }

    @PostMapping("/update")
    public void updateProgress(
            @RequestParam Long studentId,
            @RequestParam Long lessonMaterialId
    ) {
        studentProgressService.updateProgress(studentId, lessonMaterialId);
    }
}

