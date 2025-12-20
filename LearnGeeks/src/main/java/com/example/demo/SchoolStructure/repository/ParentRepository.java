package com.example.demo.SchoolStructure.repository;

import com.example.demo.SchoolStructure.Model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    List<Parent> findByStudentId(Long studentId);
}
