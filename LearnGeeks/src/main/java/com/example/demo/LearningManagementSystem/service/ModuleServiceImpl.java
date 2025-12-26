package com.example.demo.learningmanagementsystem.service;




import com.example.demo.learningmanagementsystem.dto.CreateModuleRequest;
import com.example.demo.learningmanagementsystem.dto.ModuleResponse;
import com.example.demo.learningmanagementsystem.model.Course;
import com.example.demo.learningmanagementsystem.model.Module;
import com.example.demo.learningmanagementsystem.repository.CourseRepository;
import com.example.demo.learningmanagementsystem.repository.ModuleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    @Override
    public ModuleResponse createModule(CreateModuleRequest request) {
        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Module module = Module.builder()
                .title(request.title())
                .description(request.description())
                .course(course)
                .build();

        moduleRepository.save(module);

        return new ModuleResponse(
                module.getId(),
                module.getTitle(),
                module.getDescription(),
                List.of()
        );
    }

    @Override
    public List<ModuleResponse> getModulesByCourse(Long courseId) {
        return moduleRepository.findByCourseId(courseId)
                .stream()
                .map(m -> new ModuleResponse(
                        m.getId(),
                        m.getTitle(),
                        m.getDescription(),
                        List.of()
                ))
                .toList();
    }
}
