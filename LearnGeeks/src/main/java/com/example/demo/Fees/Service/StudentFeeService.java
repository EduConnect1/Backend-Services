package com.example.demo.Fees.Service;

import com.example.demo.Fees.DTO.StudentFeeRequest;
import com.example.demo.Fees.DTO.StudentFeeResponse;

import java.util.List;

public interface StudentFeeService {

    void assignFeeToStudent(StudentFeeRequest request);

    List<StudentFeeResponse> getStudentFees(Long studentId);
}
