package com.example.demo.SchoolStructure.service;
import com.example.demo.SchoolStructure.model.Teacher;
import java.util.Set;


public class TeacherService {
    

    Teacher createTeacher(Teacher teacher);

    Teacher assignSubjects(Long teacherId, Set<Long> subjectIds);

    Teacher getTeacherById(Long id);

    void deleteTeacher(Long id);
}

    

