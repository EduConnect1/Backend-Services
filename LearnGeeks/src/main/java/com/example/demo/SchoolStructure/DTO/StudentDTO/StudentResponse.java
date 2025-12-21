package com.example.demo.SchoolStructure.DTO.StudentDTO;

public record StudentResponse(
    Long id,
    String admissionNumber,
    Long userId,
    Long classId,
    String className
) {}
