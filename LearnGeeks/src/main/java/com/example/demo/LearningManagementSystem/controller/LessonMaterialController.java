package com.example.demo.learningmanagementsystem.controller;
import com.example.demo.learningmanagementsystem.dto.CreateLessonMaterialRequest;
import com.example.demo.learningmanagementsystem.dto.LessonMaterialResponse;
import com.example.demo.learningmanagementsystem.service.LessonMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lms/materials")
@RequiredArgsConstructor
public class LessonMaterialController {

    private final LessonMaterialService lessonMaterialService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonMaterialResponse createLessonMaterial(
            @Valid @RequestBody CreateLessonMaterialRequest request
    ) {
        return lessonMaterialService.createLessonMaterial(request);
    }

    @GetMapping("/module/{moduleId}")
    public List<LessonMaterialResponse> getMaterialsByModule(
            @PathVariable Long moduleId
    ) {
        return lessonMaterialService.getMaterialsByModule(moduleId);
    }
}
