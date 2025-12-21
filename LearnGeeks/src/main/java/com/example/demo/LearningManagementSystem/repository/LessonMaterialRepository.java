package com.example.demo.LearningManagementSystem.repository;

import com.example.demo.LearningManagementSystem.Model.LessonMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonMaterialRepository extends JpaRepository<LessonMaterial, Long> {

    List<LessonMaterial> findByModuleId(Long moduleId);
}

