package com.example.demo.LearningManagementSystem.service;



import com.example.demo.LearningManagementSystem.DTO.CreateCourseRequest;
import com.example.demo.LearningManagementSystem.DTO.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);

    CourseResponse getCourseById(Long courseId);

    List<CourseResponse> getAllCourses();

    List<CourseResponse> getCoursesByTeacher(Long teacherId);
}

