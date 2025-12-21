package com.example.demo.SchoolStructure.DTO.TeacherDTO;

import java.util.Set;

public record TeacherResponse(
    Long id,
    Long userId,
    String employeeNumber,
    Set<String> subjects
) {}
