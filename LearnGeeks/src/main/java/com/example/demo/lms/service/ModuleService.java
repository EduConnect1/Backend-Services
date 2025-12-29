package com.example.demo.lms.service;



import com.example.demo.lms.dto.CreateModuleRequest;
import com.example.demo.lms.dto.ModuleResponse;

import java.util.List;

public interface ModuleService {

    ModuleResponse createModule(CreateModuleRequest request);

    List<ModuleResponse> getModulesByCourse(Long courseId);
}
