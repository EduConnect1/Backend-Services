package com.example.demo.SchoolStructure.DTO.TeacherDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponse {

    private Long id;
    private Long userId;
    private String employeeNumber;
    private Set<String> subjects;
}
