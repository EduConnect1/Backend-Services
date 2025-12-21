package com.example.demo.LearningManagementSystem.repository;


import com.example.demo.LearningManagementSystem.Model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findByCourseId(Long courseId);
}
