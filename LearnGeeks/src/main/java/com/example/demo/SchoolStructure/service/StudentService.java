package com.example.demo.SchoolStructure.service;

import com.example.demo.SchoolStructure.Model.Student;
import java.util.List;

public interface StudentService {

    Student createStudent(Long classId, Student student);

    Student getStudentById(Long id);

    List<Student> getStudentsByClass(Long classId);

    void deleteStudent(Long id);
}
