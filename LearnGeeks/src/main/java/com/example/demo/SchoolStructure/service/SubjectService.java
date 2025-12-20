package com.example.demo.SchoolStructure.service;
import com.example.demo.SchoolStructure.Model.SubjectModel;


import java.util.List;

public interface SubjectService {

    Subject createSubject(Long classId, Subject subject);

    Subject getSubjectById(Long id);

    List<Subject> getSubjectsByClass(Long classId);

    void deleteSubject(Long id);
}
