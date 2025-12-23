package com.example.demo.Fees.Repository;

import com.example.demo.Fees.Model.StudentFee;
import com.example.demo.SchoolStructure.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentFeeRepository extends JpaRepository<StudentFee, Long> {
    List<StudentFee> findByStudent(Student student);
}
