package com.example.demo.SchoolStructure.controller;

import com.example.demo.SchoolStructure.DTO.ParentDTO.CreateParentRequest;
import com.example.demo.SchoolStructure.DTO.ParentDTO.ParentResponse;
import com.example.demo.SchoolStructure.Model.Parent;
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
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Parent parent = Parent.builder()
                .user(user)
                .build();

        Parent saved = parentService.createParent(request.studentId(), parent);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ParentResponse(
                        saved.getId(),
                        saved.getUser().getId(),
                        saved.getStudent().getId(),
                        saved.getStudent().getAdmissionNumber()
                ));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ParentResponse>> getParentsByStudent(
            @PathVariable Long studentId
    ) {
        List<ParentResponse> response = parentService.getParentsByStudent(studentId)
                .stream()
                .map(p -> new ParentResponse(
                        p.getId(),
                        p.getUser().getId(),
                        p.getStudent().getId(),
                        p.getStudent().getAdmissionNumber()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
}
