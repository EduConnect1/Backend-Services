package com.example.demo.fees.service;

import com.example.demo.fees.dto.StudentFeeRequest;
import com.example.demo.fees.dto.StudentFeeResponse;
import com.example.demo.fees.model.FeeStructure;
import com.example.demo.fees.model.StudentFee;
import com.example.demo.fees.repository.FeeStructureRepository;
import com.example.demo.fees.repository.StudentFeeRepository;
import com.example.demo.schoolstructure.model.Student;
import com.example.demo.schoolstructure.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentFeeServiceImpl implements StudentFeeService {

    private final StudentFeeRepository studentFeeRepository;
    private final StudentRepository studentRepository;
    private final FeeStructureRepository feeStructureRepository;

    @Override
    public void assignFeeToStudent(StudentFeeRequest request) {

        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        FeeStructure feeStructure = feeStructureRepository.findById(request.feeStructureId())
                .orElseThrow(() -> new RuntimeException("Fee structure not found"));

        StudentFee studentFee = StudentFee.builder()
                .student(student)
                .feeStructure(feeStructure)
                .amountDue(feeStructure.getAmount())
                .amountPaid(0.0)
                .fullyPaid(false)
                .build();

        studentFeeRepository.save(studentFee);
    }

    @Override
    public List<StudentFeeResponse> getStudentFees(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return studentFeeRepository.findByStudent(student)
                .stream()
                .map(f -> new StudentFeeResponse(
                        f.getId(),
                        student.getId(),
                        f.getFeeStructure().getId(),
                        f.getAmountDue(),
                        f.getAmountPaid(),
                        f.getFullyPaid()
                ))
                .toList();
    }
}

