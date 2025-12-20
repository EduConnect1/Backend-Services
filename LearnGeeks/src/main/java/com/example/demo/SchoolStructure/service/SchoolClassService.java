package com.example.demo.SchoolStructure.service;

import com.example.demo.SchoolStructure.Model.SchoolClass;
import java.util.List;

public interface SchoolClassService {

    SchoolClass createClass(SchoolClass schoolClass);

    SchoolClass getClassById(Long id);

    List<SchoolClass> getAllClasses();

    void deleteClass(Long id);
}
