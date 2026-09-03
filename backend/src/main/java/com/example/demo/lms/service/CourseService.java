package com.example.demo.lms.service;

import com.example.demo.lms.dto.CreateCourseRequest;
import com.example.demo.lms.dto.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);

    CourseResponse getCourseById(Long courseId);

    List<CourseResponse> getAllCourses();

    List<CourseResponse> getCoursesByTeacher(Long teacherId);
}

