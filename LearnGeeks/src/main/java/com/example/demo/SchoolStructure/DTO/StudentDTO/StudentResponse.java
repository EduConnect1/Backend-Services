package com.example.demo.SchoolStructure.DTO.StudentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Long id;
    private String admissionNumber;
    private Long userId;
    private Long classId;
    private String className;
}
