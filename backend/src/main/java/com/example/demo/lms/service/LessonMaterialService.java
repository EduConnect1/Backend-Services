package com.example.demo.lms.service;

import com.example.demo.lms.dto.CreateLessonMaterialRequest;
import com.example.demo.lms.dto.LessonMaterialResponse;

import java.util.List;

public interface LessonMaterialService {

    LessonMaterialResponse createLessonMaterial(CreateLessonMaterialRequest request);

    List<LessonMaterialResponse> getMaterialsByModule(Long moduleId);
}
