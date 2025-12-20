package com.example.demo.SchoolStructure.controller;

import com.example.demo.SchoolStructure.DTO.ClassDTO.CreateClassRequest;
import com.example.demo.SchoolStructure.DTO.ClassDTO.ClassResponse;
import com.example.demo.SchoolStructure.Model.ClassModel;
import com.example.demo.SchoolStructure.service.ClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController

@RequestMapping("/api/classes")
@RequiredArgsConstructor


public class SchoolClassController {
   
    private final ClassService classService;

    @PostMapping
    public ResponseEntity<ClassResponse> createClass(
            @Valid @RequestBody CreateClassRequest request
    ) {
        ClassModel ClassModel = ClassModel.builder()
                .name(request.getName())
                .academicYear(request.getAcademicYear())
                .build();

        ClassModel saved = classService.createClass(ClassModel);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClassResponse.builder()
                        .id(saved.getId())
                        .name(saved.getName())
                        .academicYear(saved.getAcademicYear())
                        .build());
    }

    @GetMapping
    public ResponseEntity<List<ClassResponse>> getAllClasses() {
        List<ClassResponse> response = ClassService.getAllClasses()
                .stream()
                .map(c -> SchoolClassResponse.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .academicYear(c.getAcademicYear())
                        .build())
                .toList();

        return ResponseEntity.ok(response);
    }
}

    

