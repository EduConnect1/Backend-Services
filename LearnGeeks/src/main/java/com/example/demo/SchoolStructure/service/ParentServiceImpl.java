package com.example.demo.schoolstructure.service;

import com.example.demo.schoolstructure.model.Parent;
import com.example.demo.schoolstructure.model.Student;
import com.example.demo.schoolstructure.repository.ParentRepository;
import com.example.demo.schoolstructure.repository.StudentRepository;
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
    public List<Parent> getParentsByStudent_Id(Long student_Id) {
        return parentRepository.findByStudent_Id(student_Id);
    }

    @Override
    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }
}
