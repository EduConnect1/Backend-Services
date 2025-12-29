package com.example.demo.schoolstructure.dto.parentdto;

public record ParentResponse(
    Long id,
    Long userId,
    Long studentId,
    String studentAdmissionNumber
) {}
