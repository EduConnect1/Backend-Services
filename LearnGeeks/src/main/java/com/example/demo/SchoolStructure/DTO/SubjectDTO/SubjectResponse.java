package com.example.demo.SchoolStructure.DTO.SubjectDTO;

public record SubjectResponse(
    Long id,
    String name,
    Long classId,
    String className
) {}
