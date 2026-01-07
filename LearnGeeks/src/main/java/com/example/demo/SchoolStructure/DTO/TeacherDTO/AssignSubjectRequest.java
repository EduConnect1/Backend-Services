package com.example.demo.schoolstructure.dto.teacherdto;

import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record AssignSubjectRequest(
    @NotEmpty(message = "Subject IDs are required")
    Set<Long> subjectIds
) {}
