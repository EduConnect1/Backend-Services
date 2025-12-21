package com.example.demo.LearningManagementSystem.DTO;

import java.util.List;

public record ModuleResponse(
        Long id,
        String title,
        String description,
        List<LessonMaterialResponse> lessonMaterials
) {
}
