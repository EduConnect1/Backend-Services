package com.example.demo.LearningManagementSystem.service;

import com.example.demo.LearningManagementSystem.DTO.CreateLessonMaterialRequest;
import com.example.demo.LearningManagementSystem.DTO.LessonMaterialResponse;

import java.util.List;

public interface LessonMaterialService {

    LessonMaterialResponse createLessonMaterial(CreateLessonMaterialRequest request);

    List<LessonMaterialResponse> getMaterialsByModule(Long moduleId);
}
