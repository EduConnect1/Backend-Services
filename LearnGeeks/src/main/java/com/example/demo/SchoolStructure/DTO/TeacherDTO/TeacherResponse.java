package com.example.demo.schoolstructure.dto.teacherdto;

import java.util.Set;

public record TeacherResponse(
    Long id,
    Long userId,
    String employeeNumber,
    Set<String> subjects
) {}
