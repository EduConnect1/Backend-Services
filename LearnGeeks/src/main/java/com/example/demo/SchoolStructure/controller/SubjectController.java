package com.example.demo.SchoolStructure.controller;
import com.example.demo.SchoolStructure.DTO.SubjectDTO.CreateSubjectRequest;
import com.example.demo.SchoolStructure.DTO.SubjectDTO.SubjectResponse;
import com.example.demo.SchoolStructure.Model.SubjectModel;
import com.example.demo.SchoolStructure.service.SubjectService;
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
                .name(request.getName())
                .build();

        Subject saved = subjectService.createSubject(request.getClassId(), subject);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SubjectResponse.builder()
                        .id(saved.getId())
                        .name(saved.getName())
                        .classId(saved.getSchoolClass().getId())
                        .className(saved.getSchoolClass().getName())
                        .build());
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<SubjectResponse>> getSubjectsByClass(
            @PathVariable Long classId
    ) {
        List<SubjectResponse> response = subjectService.getSubjectsByClass(classId)
                .stream()
                .map(s -> SubjectResponse.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .classId(s.getSchoolClass().getId())
                        .className(s.getSchoolClass().getName())
                        .build())
                .toList();

        return ResponseEntity.ok(response);
    }
}

    

