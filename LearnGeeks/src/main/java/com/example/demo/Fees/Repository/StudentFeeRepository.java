package com.example.demo.fees.repository;

import com.example.demo.fees.model.StudentFee;
import com.example.demo.schoolstructure.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentFeeRepository extends JpaRepository<StudentFee, Long> {
    List<StudentFee> findByStudent(Student student);
}
