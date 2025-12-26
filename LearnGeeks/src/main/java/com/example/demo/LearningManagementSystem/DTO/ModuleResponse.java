package com.example.demo.learningmanagementsystem.dto;

import java.util.List;

public record ModuleResponse(
        Long id,
        String title,
        String description,
        List<LessonMaterialResponse> lessonMaterials
) {
}
