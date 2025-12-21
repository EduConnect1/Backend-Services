package com.example.demo.LearningManagementSystem.service;

import com.example.demo.LearningManagementSystem.DTO.CreateLessonMaterialRequest;
import com.example.demo.LearningManagementSystem.DTO.LessonMaterialResponse;
import com.example.demo.LearningManagementSystem.Model.LessonMaterial;
import com.example.demo.LearningManagementSystem.Model.Module;
import com.example.demo.LearningManagementSystem.repository.LessonMaterialRepository;
import com.example.demo.LearningManagementSystem.repository.ModuleRepository;
// import com.example.demo.LearningManagementSystem.service.LessonMaterialService;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonMaterialServiceImpl implements LessonMaterialService {

    private final LessonMaterialRepository lessonMaterialRepository;
    private final ModuleRepository moduleRepository;

    @Override
    public LessonMaterialResponse createLessonMaterial(CreateLessonMaterialRequest request) {
        Module module = moduleRepository.findById(request.moduleId())
                .orElseThrow(() -> new RuntimeException("Module not found"));

        LessonMaterial material = LessonMaterial.builder()
                .title(request.title())
                .contentUrl(request.contentUrl())
                .module(module)
                .build();

        lessonMaterialRepository.save(material);

        return new LessonMaterialResponse(
                material.getId(),
                material.getTitle(),
                material.getContentUrl()
        );
    }

    @Override
    public List<LessonMaterialResponse> getMaterialsByModule(Long moduleId) {
        return lessonMaterialRepository.findByModuleId(moduleId)
                .stream()
                .map(m -> new LessonMaterialResponse(
                        m.getId(),
                        m.getTitle(),
                        m.getContentUrl()
                ))
                .toList();
    }
}

