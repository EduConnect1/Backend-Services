package com.example.demo.SchoolStructure.controller;

import com.example.demo.SchoolStructure.DTO.StudentDTO.CreateStudentRequest;
import com.example.demo.SchoolStructure.DTO.StudentDTO.StudentResponse;
import com.example.demo.SchoolStructure.Model.Student;
import com.example.demo.SchoolStructure.service.StudentService;
import com.example.demo.User.Model.User;
import com.example.demo.User.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody CreateStudentRequest request
    ) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = Student.builder()
                .user(user)
                .admissionNumber(request.admissionNumber())
                .build();

        Student saved = studentService.createStudent(request.classId(), student);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StudentResponse(
                        saved.getId(),
                        saved.getAdmissionNumber(),
                        saved.getUser().getId(),
                        saved.getSchoolClass().getId(),
                        saved.getSchoolClass().getName()
                ));
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<StudentResponse>> getStudentsByClass(
            @PathVariable Long classId
    ) {
        List<StudentResponse> response = studentService.getStudentsByClass(classId)
                .stream()
                .map(s -> new StudentResponse(
                        s.getId(),
                        s.getAdmissionNumber(),
                        s.getUser().getId(),
                        s.getSchoolClass().getId(),
                        s.getSchoolClass().getName()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
}
