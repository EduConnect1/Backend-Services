package com.example.demo.schoolstructure.service;

import com.example.demo.schoolstructure.model.Student;
import java.util.List;

public interface StudentService {

    Student createStudent(Long classId, Student student);

    Student getStudentById(Long id);

    List<Student> getStudentsByClass(Long classId);

    void deleteStudent(Long id);
}
