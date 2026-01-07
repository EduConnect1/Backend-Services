package com.example.demo.schoolstructure.controller;

import com.example.demo.schoolstructure.dto.classdto.CreateClassRequest;
import com.example.demo.schoolstructure.dto.classdto.ClassResponse;
import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.service.SchoolClassService;

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
   
    private final SchoolClassService schoolClassService;

    @PostMapping
    public ResponseEntity<ClassResponse> createClass(
            @Valid @RequestBody CreateClassRequest request
    ) {
        SchoolClass schoolClass = SchoolClass.builder()
                .name(request.name())
                .academicYear(request.academicYear())
                .build();

        SchoolClass saved = schoolClassService.createClass(schoolClass);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ClassResponse(
                        saved.getId(),
                        saved.getName(),
                        saved.getAcademicYear()
                ));
    }

    @GetMapping
    public ResponseEntity<List<ClassResponse>> getAllClasses() {
        List<ClassResponse> response = schoolClassService.getAllClasses()
                .stream()
                .map(c -> new ClassResponse(
                        c.getId(),
                        c.getName(),
                        c.getAcademicYear()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
}
