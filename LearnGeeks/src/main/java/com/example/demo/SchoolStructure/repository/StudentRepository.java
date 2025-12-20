package com.example.demo.SchoolStructure.repository;


import com.example.demo.SchoolStructure.Model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {

    List<StudentModel> findBySchoolClassId(Long classId);

    Optional<StudentModel> findByAdmissionNumber(String admissionNumber);

    boolean existsByAdmissionNumber(String admissionNumber);
}
