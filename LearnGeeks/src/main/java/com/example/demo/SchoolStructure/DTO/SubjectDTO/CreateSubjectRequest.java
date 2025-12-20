package com.example.demo.SchoolStructure.DTO.SubjectDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSubjectRequest {
    
    @NotBlank(message = "Subject name is required")
    private String name;

    @NotNull(message = "Class ID is required")
    private Long classId;
}

    

