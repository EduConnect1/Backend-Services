package com.example.demo.schoolstructure.service;

import com.example.demo.schoolstructure.model.Subject;
import java.util.List;

public interface SubjectService {

    Subject createSubject(Long classId, Subject subject);

    Subject getSubjectById(Long id);

    List<Subject> getSubjectsByClass(Long classId);

    void deleteSubject(Long id);
}
