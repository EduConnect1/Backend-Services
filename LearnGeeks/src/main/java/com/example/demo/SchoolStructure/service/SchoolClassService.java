package com.example.demo.schoolstructure.service;

import com.example.demo.schoolstructure.model.SchoolClass;
import java.util.List;

public interface SchoolClassService {

    SchoolClass createClass(SchoolClass schoolClass);

    SchoolClass getClassById(Long id);

    List<SchoolClass> getAllClasses();

    void deleteClass(Long id);
}
