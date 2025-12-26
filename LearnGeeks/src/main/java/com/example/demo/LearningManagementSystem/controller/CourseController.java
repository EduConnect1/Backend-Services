package com.example.demo.learningmanagementsystem.controller;
import com.example.demo.learningmanagementsystem.dto.CourseResponse;
import com.example.demo.learningmanagementsystem.dto.CreateCourseRequest;
import com.example.demo.learningmanagementsystem.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lms/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(
            @Valid @RequestBody CreateCourseRequest request
    ) {
        return courseService.createCourse(request);
    }

    @GetMapping("/{courseId}")
    public CourseResponse getCourseById(
            @PathVariable Long courseId
    ) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/teacher/{teacherId}")
    public List<CourseResponse> getCoursesByTeacher(
            @PathVariable Long teacherId
    ) {
        return courseService.getCoursesByTeacher(teacherId);
    }
}
