package com.example.demo.SchoolStructure.controller;
import com.example.demo.SchoolStructure.DTO.TeacherDTO.AssignSubjectRequest;
import com.example.demo.SchoolStructure.DTO.TeacherDTO.CreateTeacherRequest;
import com.example.demo.SchoolStructure.DTO.TeacherDTO.TeacherResponse;
import com.example.demo.SchoolStructure.Model.Teacher;
import com.example.demo.SchoolStructure.service.TeacherService;
import com.example.demo.auth.entity.User;
import com.example.demo.auth.repository.UserRepository;
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
