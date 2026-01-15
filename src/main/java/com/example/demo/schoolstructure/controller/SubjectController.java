package com.example.demo.schoolstructure.controller;

import com.example.demo.schoolstructure.dto.subjectdto.CreateSubjectRequest;
import com.example.demo.schoolstructure.dto.subjectdto.SubjectResponse;
import com.example.demo.schoolstructure.model.Subject;
import com.example.demo.schoolstructure.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResponse> createSubject(
            @Valid @RequestBody CreateSubjectRequest request
    ) {
        Subject subject = Subject.builder()
                .name(request.name())
                .build();

        Subject saved = subjectService.createSubject(request.classId(), subject);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SubjectResponse(
                        saved.getId(),
                        saved.getName(),
                        saved.getSchoolClass().getId(),
                        saved.getSchoolClass().getName()
                ));
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<SubjectResponse>> getSubjectsByClass(
            @PathVariable Long classId
    ) {
        List<SubjectResponse> response = subjectService.getSubjectsByClass(classId)
                .stream()
                .map(s -> new SubjectResponse(
                        s.getId(),
                        s.getName(),
                        s.getSchoolClass().getId(),
                        s.getSchoolClass().getName()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
}
