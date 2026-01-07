package com.example.demo.schoolstructure.repository;

import com.example.demo.schoolstructure.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    boolean existsByNameAndAcademicYear(String name, String academicYear);
}
