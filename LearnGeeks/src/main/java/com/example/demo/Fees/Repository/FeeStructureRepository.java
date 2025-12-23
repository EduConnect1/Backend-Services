package com.example.demo.Fees.Repository;

import com.example.demo.Fees.Model.FeeStructure;
import com.example.demo.SchoolStructure.Model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {
    List<FeeStructure> findBySchoolClass(SchoolClass schoolClass);
}
