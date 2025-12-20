package com.example.demo.SchoolStructure.service;

import com.example.demo.SchoolStructure.Model.SchoolClass;
import com.example.demo.SchoolStructure.Model.Subject;
import com.example.demo.SchoolStructure.repository.SchoolClassRepository;
import com.example.demo.SchoolStructure.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SchoolClassRepository schoolClassRepository;

    @Override
    public Subject createSubject(Long classId, Subject subject) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        subject.setSchoolClass(schoolClass);
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    @Override
    public List<Subject> getSubjectsByClass(Long classId) {
        return subjectRepository.findBySchoolClassId(classId);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
