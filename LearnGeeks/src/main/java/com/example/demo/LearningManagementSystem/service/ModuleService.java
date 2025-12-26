package com.example.demo.learningmanagementsystem.service;



import com.example.demo.learningmanagementsystem.dto.CreateModuleRequest;
import com.example.demo.learningmanagementsystem.dto.ModuleResponse;

import java.util.List;

public interface ModuleService {

    ModuleResponse createModule(CreateModuleRequest request);

    List<ModuleResponse> getModulesByCourse(Long courseId);
}
