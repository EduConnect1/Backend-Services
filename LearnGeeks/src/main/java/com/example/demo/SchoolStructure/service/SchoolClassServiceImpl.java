package com.example.demo.schoolstructure.service;

import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.repository.SchoolClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;

    @Override
    public SchoolClass createClass(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    @Override
    public SchoolClass getClassById(Long id) {
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
