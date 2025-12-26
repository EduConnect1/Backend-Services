package com.example.demo.learningmanagementsystem.service;

import com.example.demo.learningmanagementsystem.dto.CreateLessonMaterialRequest;
import com.example.demo.learningmanagementsystem.dto.LessonMaterialResponse;

import java.util.List;

public interface LessonMaterialService {

    LessonMaterialResponse createLessonMaterial(CreateLessonMaterialRequest request);

    List<LessonMaterialResponse> getMaterialsByModule(Long moduleId);
}
