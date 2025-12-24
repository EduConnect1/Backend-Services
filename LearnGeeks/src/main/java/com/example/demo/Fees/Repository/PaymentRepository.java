package com.example.demo.Fees.Repository;

import com.example.demo.Fees.Model.Payment;
import com.example.demo.SchoolStructure.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudent(Student student);
}
