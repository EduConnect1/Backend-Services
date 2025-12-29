package com.example.demo.fees.controller;

import com.example.demo.fees.dto.StudentFeeRequest;
import com.example.demo.fees.dto.StudentFeeResponse;
import com.example.demo.fees.service.StudentFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentFeeController {

    private final StudentFeeService studentFeeService;

    @PostMapping("/{studentId}/fees")
    public ResponseEntity<Void> assignFeeToStudent(
            @PathVariable Long studentId,
            @RequestBody StudentFeeRequest request) {

        studentFeeService.assignFeeToStudent(
                new StudentFeeRequest(studentId, request.feeStructureId())
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{studentId}/fees")
    public ResponseEntity<List<StudentFeeResponse>> getStudentFees(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                studentFeeService.getStudentFees(studentId)
        );
    }
}

