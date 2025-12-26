package com.example.demo.schoolstructure.repository;

import com.example.demo.schoolstructure.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findBySchoolClassId(Long classId);

    boolean existsByNameAndSchoolClassId(String name, Long classId);
}
