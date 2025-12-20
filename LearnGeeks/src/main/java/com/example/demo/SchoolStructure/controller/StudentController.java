package com.example.demo.SchoolStructure.controller;
import com.example.demo.SchoolStructure.DTO.StudentDTO.CreateStudentRequest;
import com.example.demo.SchoolStructure.DTO.StudentDTO.StudentResponse;
import com.example.demo.SchoolStructure.Model.StudentModel;
import com.example.demo.SchoolStructure.Model.User;
import com.example.demo.SchoolStructure.repository.UserRepository;
import com.example.demo.SchoolStructure.service.StudentService;
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
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = Student.builder()
                .user(user)
                .admissionNumber(request.getAdmissionNumber())
                .build();

        Student saved = studentService.createStudent(request.getClassId(), student);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StudentResponse.builder()
                        .id(saved.getId())
                        .admissionNumber(saved.getAdmissionNumber())
                        .userId(saved.getUser().getId())
                        .classId(saved.getSchoolClass().getId())
                        .className(saved.getSchoolClass().getName())
                        .build());
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<StudentResponse>> getStudentsByClass(
            @PathVariable Long classId
    ) {
        List<StudentResponse> response = studentService.getStudentsByClass(classId)
                .stream()
                .map(s -> StudentResponse.builder()
                        .id(s.getId())
                        .admissionNumber(s.getAdmissionNumber())
                        .userId(s.getUser().getId())
                        .classId(s.getSchoolClass().getId())
                        .className(s.getSchoolClass().getName())
                        .build())
                .toList();

        return ResponseEntity.ok(response);
    }
}

    

