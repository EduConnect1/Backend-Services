package com.example.demo.LearningManagementSystem.service;



import com.example.demo.LearningManagementSystem.DTO.CreateModuleRequest;
import com.example.demo.LearningManagementSystem.DTO.ModuleResponse;

import java.util.List;

public interface ModuleService {

    ModuleResponse createModule(CreateModuleRequest request);

    List<ModuleResponse> getModulesByCourse(Long courseId);
}
