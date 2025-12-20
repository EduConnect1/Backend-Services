package com.example.demo.SchoolStructure.DTO.TeacherDTO;
import lombok.Data;
import java.util.Set;
@Data


public class TeacherResponse {
    

    private Long id;
    private Long userId;
    private String employeeNumber;
    private Set<String> subjects;
}

    

