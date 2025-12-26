package com.example.demo.learningmanagementsystem.controller;

import com.example.demo.learningmanagementsystem.dto.CreateModuleRequest;
import com.example.demo.learningmanagementsystem.dto.ModuleResponse;
import com.example.demo.learningmanagementsystem.service.ModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lms/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModuleResponse createModule(
            @Valid @RequestBody CreateModuleRequest request
    ) {
        return moduleService.createModule(request);
    }

    @GetMapping("/course/{courseId}")
    public List<ModuleResponse> getModulesByCourse(
            @PathVariable Long courseId
    ) {
        return moduleService.getModulesByCourse(courseId);
    }
}
