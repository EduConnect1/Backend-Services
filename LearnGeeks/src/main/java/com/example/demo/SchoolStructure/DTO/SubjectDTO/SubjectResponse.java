package com.example.demo.SchoolStructure.DTO.SubjectDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponse {
    
    private Long id;
    private String name;
    private Long classId;
    private String className;
}
