package com.example.demo.ResultManagement.Controller;



import com.example.demo.ResultManagement.DTO.ExamResultRequest;
import com.example.demo.ResultManagement.DTO.ExamResultResponse;
import com.example.demo.ResultManagement.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ExamResultController {

    private final ExamResultService resultService;

    @PostMapping
    public ResponseEntity<ExamResultResponse> addOrUpdateResult(@RequestBody ExamResultRequest request) {
        return ResponseEntity.ok(resultService.addOrUpdateResult(request));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamResultResponse>> getStudentResults(@PathVariable Long studentId) {
        return ResponseEntity.ok(resultService.getResultsByStudent(studentId));
    }
}

