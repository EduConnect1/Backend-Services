package com.example.demo.SchoolStructure.DTO.TeacherDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class AssignSubjectRequest {

    @NotEmpty(message = "Subject IDs are required")
    private Set<Long> subjectIds;
}
