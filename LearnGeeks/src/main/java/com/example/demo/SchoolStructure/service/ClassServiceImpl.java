package com.example.demo.SchoolStructure.service;
import com.example.demo.SchoolStructure.Model.ClassModel;
import com.example.demo.SchoolStructure.model.SchoolClass;
import com.example.demo.SchoolStructure.repository.SchoolClassRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ClassServiceImpl implements ClassService {
    private final SchoolClassRepository schoolClassRepository;

    @Override
    public ClassModel createClass(ClassModel ClassModel) {
        return schoolClassRepository.save(ClassModel);
    }
    @Override
    public ClassModel getClassById(Long id) {
        return schoolClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
    }
    @Override
    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findAll();
    }
    @Override
    public void deleteClass(Long id) {
        schoolClassRepository.deleteById(id);
    }
}

    

