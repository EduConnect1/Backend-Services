package com.example.demo.SchoolStructure.controller;
import com.example.demo.SchoolStructure.Model.ParentModel;
import com.example.demo.SchoolStructure.DTO.ParentDTO.CreateParentRequest;
import com.example.demo.SchoolStructure.DTO.ParentDTO.ParentResponse;
import com.example.demo.SchoolStructure.service.ParentService;
import com.example.demo.User.Model.User;
import com.example.demo.User.Repository.UserRepository;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor


public class ParentController {
    

    private final ParentService parentService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ParentResponse> createParent(
            @Valid @RequestBody CreateParentRequest request
    ) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Parent parent = Parent.builder()
                .user(user)
                .build();

        Parent saved = parentService.createParent(request.getStudentId(), parent);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ParentResponse.builder()
                        .id(saved.getId())
                        .userId(saved.getUser().getId())
                        .studentId(saved.getStudent().getId())
                        .studentAdmissionNumber(saved.getStudent().getAdmissionNumber())
                        .build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ParentResponse>> getParentsByStudent(
            @PathVariable Long studentId
    ) {
        List<ParentResponse> response = parentService.getParentsByStudent(studentId)
                .stream()
                .map(p -> ParentResponse.builder()
                        .id(p.getId())
                        .userId(p.getUser().getId())
                        .studentId(p.getStudent().getId())
                        .studentAdmissionNumber(p.getStudent().getAdmissionNumber())
                        .build())
                .toList();

        return ResponseEntity.ok(response);
    }
}

    

