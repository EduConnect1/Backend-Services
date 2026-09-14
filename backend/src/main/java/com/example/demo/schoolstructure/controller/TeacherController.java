package com.example.demo.schoolstructure.controller;
import com.example.demo.schoolstructure.dto.teacherdto.AssignSubjectRequest;
import com.example.demo.schoolstructure.dto.teacherdto.CreateTeacherRequest;
import com.example.demo.schoolstructure.dto.teacherdto.TeacherResponse;
import com.example.demo.schoolstructure.model.Teacher;
import com.example.demo.schoolstructure.service.TeacherService;
import com.example.demo.auth.entity.User;
import com.example.demo.auth.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Teacher teacher = Teacher.builder()
                .user(user)
                .employeeNumber(request.employeeNumber())
                .build();

        Teacher saved = teacherService.createTeacher(teacher);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TeacherResponse(
                        saved.getId(),
                        saved.getUser().getId(),
                        saved.getEmployeeNumber(),
                        null 
                ));
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        List<TeacherResponse> response = teacherService.getAllTeachers()
                .stream()
                .map(t -> new TeacherResponse(
                        t.getId(),
                        t.getUser().getId(),
                        t.getEmployeeNumber(),
                        t.getSubjects() != null ? t.getSubjects().stream().map(s -> s.getName()).collect(Collectors.toSet()) : java.util.Collections.emptySet()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{teacherId}/subjects")
    public ResponseEntity<TeacherResponse> assignSubjects(
            @PathVariable Long teacherId,
            @Valid @RequestBody AssignSubjectRequest request
    ) {
        Teacher updated = teacherService.assignSubjects(teacherId, request.subjectIds());

        return ResponseEntity.ok(
                new TeacherResponse(
                        updated.getId(),
                        updated.getUser().getId(),
                        updated.getEmployeeNumber(),
                        updated.getSubjects()
                                .stream()
                                .map(s -> s.getName())
                                .collect(Collectors.toSet())
                )
        );
    }
}
