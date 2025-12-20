package com.example.demo.SchoolStructure.service;
import com.example.demo.SchoolStructure.Model.StudentModel;
import com.example.demo.SchoolStructure.Model.ClassModel;
import com.example.demo.SchoolStructure.repository.StudentRepository;
import com.example.demo.SchoolStructure.repository.SchoolClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SchoolClassRepository schoolClassRepository;

    @Override
    public StudentModel createStudent(Long classId, StudentModel  student) {
        ClassModel schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        student.setClassModel(schoolClass);
        return studentRepository.save(student);
    }

    @Override
    public StudentModel getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<StudentModel> getStudentsByClass(Long classId) {
        return studentRepository.findByClassModelId(classId);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
