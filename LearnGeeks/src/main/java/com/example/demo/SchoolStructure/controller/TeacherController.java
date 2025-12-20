package com.example.demo.SchoolStructure.controller;



import com.example.demo.SchoolStructure.DTO.TeacherDTO.AssignSubjectRequest;
import com.example.demo.SchoolStructure.DTO.TeacherDTO.CreateTeacherRequest;
import com.example.demo.SchoolStructure.DTO.TeacherDTO.TeacherResponse;
import com.example.demo.SchoolStructure.Model.Teacher;
import com.example.demo.SchoolStructure.service.TeacherService;
import com.example.demo.User.Model.User;
import com.example.demo.User.Repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(
            @Valid @RequestBody CreateTeacherRequest request
    ) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Teacher teacher = Teacher.builder()
                .user(user)
                .employeeNumber(request.getEmployeeNumber())
                .build();

        Teacher saved = teacherService.createTeacher(teacher);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TeacherResponse.builder()
                        .id(saved.getId())
                        .userId(saved.getUser().getId())
                        .employeeNumber(saved.getEmployeeNumber())
                        .build());
    }

    @PostMapping("/{teacherId}/subjects")
    public ResponseEntity<TeacherResponse> assignSubjects(
            @PathVariable Long teacherId,
            @Valid @RequestBody AssignSubjectRequest request
    ) {
        Teacher updated = teacherService.assignSubjects(teacherId, request.getSubjectIds());

        return ResponseEntity.ok(
                TeacherResponse.builder()
                        .id(updated.getId())
                        .userId(updated.getUser().getId())
                        .employeeNumber(updated.getEmployeeNumber())
                        .subjects(
                                updated.getSubjects()
                                        .stream()
                                        .map(s -> s.getName())
                                        .collect(Collectors.toSet())
                        )
                        .build()
        );
    }
}
