package com.example.demo.fees.repository;

import com.example.demo.fees.model.Payment;
import com.example.demo.schoolstructure.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudent(Student student);
}
