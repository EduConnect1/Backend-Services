package com.example.demo.fees.service;

import com.example.demo.fees.dto.StudentFeeRequest;
import com.example.demo.fees.dto.StudentFeeResponse;

import java.util.List;

public interface StudentFeeService {

    void assignFeeToStudent(StudentFeeRequest request);

    List<StudentFeeResponse> getStudentFees(Long studentId);
}
