package com.example.demo.lms.service;

import com.example.demo.lms.dto.CourseResponse;
import com.example.demo.lms.dto.CreateCourseRequest;
import com.example.demo.lms.model.Course;
import com.example.demo.lms.repository.CourseRepository;
import com.example.demo.schoolstructure.model.Teacher;
import com.example.demo.schoolstructure.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {
        Teacher teacher = teacherRepository.findById(request.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Course course = Course.builder()
                .title(request.title())
                .description(request.description())
                .teacher(teacher)
                .build();

        courseRepository.save(course);

        return mapToResponse(course);
    }

    @Override
    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return mapToResponse(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<CourseResponse> getCoursesByTeacher(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CourseResponse mapToResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getTeacher().getId(),
                course.getTeacher().getFullName(),
                course.getCreatedAt(),
                List.of()
        );
    }
}
