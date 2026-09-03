package com.example.demo.schoolstructure.service;

import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.model.Student;
import com.example.demo.schoolstructure.repository.SchoolClassRepository;
import com.example.demo.schoolstructure.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SchoolClassRepository schoolClassRepository;

    @Override
    public Student createStudent(Long classId, Student student) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        student.setSchoolClass(schoolClass);
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<Student> getStudentsByClass(Long classId) {
        return studentRepository.findBySchoolClassId(classId);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
