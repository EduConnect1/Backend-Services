package com.example.demo.schoolstructure.dto.studentdto;

public record StudentResponse(
    Long id,
    String admissionNumber,
    Long userId,
    Long classId,
    String className
) {}
