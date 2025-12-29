package com.example.demo.fees.repository;

import com.example.demo.fees.model.FeeStructure;
import com.example.demo.schoolstructure.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {
    List<FeeStructure> findBySchoolClass(SchoolClass schoolClass);
}
