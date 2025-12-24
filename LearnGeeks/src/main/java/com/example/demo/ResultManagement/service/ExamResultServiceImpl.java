package com.example.demo.ResultManagement.service;

import com.example.demo.ResultManagement.DTO.ExamResultRequest;
import com.example.demo.ResultManagement.DTO.ExamResultResponse;
import com.example.demo.ResultManagement.Model.ExamResult;
import com.example.demo.ResultManagement.Model.Subject;
import com.example.demo.ResultManagement.repository.ExamResultRepository;
import com.example.demo.ResultManagement.repository.SubjectRepository;
import com.example.demo.SchoolStructure.Model.Student;
import com.example.demo.SchoolStructure.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository resultRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public ExamResultResponse addOrUpdateResult(ExamResultRequest request) {

        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject subject = subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        
        ExamResult result = resultRepository.findByStudent(student).stream()
                .filter(r -> r.getSubject().getId().equals(subject.getId()))
                .findFirst()
                .orElse(ExamResult.builder().student(student).subject(subject).build());

        result.setMarks(request.marks());
        result.setGrade(calculateGrade(request.marks()));

        resultRepository.save(result);

        return new ExamResultResponse(result.getId(), student.getId(), subject.getId(), result.getMarks(), result.getGrade());
    }

    @Override
    public List<ExamResultResponse> getResultsByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return resultRepository.findByStudent(student).stream()
                .map(r -> new ExamResultResponse(r.getId(), student.getId(), r.getSubject().getId(), r.getMarks(), r.getGrade()))
                .collect(Collectors.toList());
    }

    @Override
    public String calculateGrade(Integer marks) {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }
}

