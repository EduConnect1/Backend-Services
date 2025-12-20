package com.example.demo.SchoolStructure.service;
import com.example.demo.SchoolStructure.Model.ParentModel;
import com.example.demo.SchoolStructure.Model.StudentModel;
import com.example.demo.SchoolStructure.repository.ParentRepository;
import com.example.demo.SchoolStructure.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;

    @Override
    public Parent createParent(Long studentId, Parent parent) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        parent.setStudent(student);
        return parentRepository.save(parent);
    }

    @Override
    public Parent getParentById(Long id) {
        return parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found"));
    }

    @Override
    public List<Parent> getParentsByStudent(Long studentId) {
        return parentRepository.findByStudentId(studentId);
    }

    @Override
    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }
}
