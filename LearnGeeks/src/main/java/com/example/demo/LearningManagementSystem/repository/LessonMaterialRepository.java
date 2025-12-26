package com.example.demo.learningmanagementsystem.repository;

import com.example.demo.learningmanagementsystem.model.LessonMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonMaterialRepository extends JpaRepository<LessonMaterial, Long> {

    List<LessonMaterial> findByModuleId(Long moduleId);
}

