package com.example.demo.SchoolStructure.DTO.ParentDTO;

public record ParentResponse(
    Long id,
    Long userId,
    Long studentId,
    String studentAdmissionNumber
) {}
