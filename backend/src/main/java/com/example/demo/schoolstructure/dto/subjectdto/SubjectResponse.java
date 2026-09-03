package com.example.demo.schoolstructure.dto.subjectdto;

public record SubjectResponse(
    Long id,
    String name,
    Long classId,
    String className
) {}
