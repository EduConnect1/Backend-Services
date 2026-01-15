package com.example.demo.schoolstructure.repository;

import com.example.demo.schoolstructure.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmployeeNumber(String employeeNumber);

    boolean existsByEmployeeNumber(String employeeNumber);
}
