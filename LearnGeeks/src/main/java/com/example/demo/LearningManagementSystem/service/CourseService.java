package com.example.demo.learningmanagementsystem.service;

import com.example.demo.learningmanagementsystem.dto.CreateCourseRequest;
import com.example.demo.learningmanagementsystem.dto.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);

    CourseResponse getCourseById(Long courseId);

    List<CourseResponse> getAllCourses();

    List<CourseResponse> getCoursesByTeacher(Long teacherId);
}

