package com.example.demo.SchoolStructure.service;
import com.example.demo.SchoolStructure.Model.StudentModel;
import java.util.List;



public interface StudentService {
    

    StudentModel createStudent(Long classId, StudentModel student);

    StudentModel getStudentById(Long id);

    List<StudentModel> getStudentsByClass(Long classId);

    void deleteStudent(Long id);
}

    

