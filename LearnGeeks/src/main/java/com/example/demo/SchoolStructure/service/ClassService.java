package com.example.demo.SchoolStructure.service;
import java.util.List;

import com.example.demo.SchoolStructure.Model.ClassModel;

public class ClassService {
    
public interface SchoolClassService {

    ClassModel createClass(ClassModel ClassModel);

    ClassModel getClassById(Long id);

    List<ClassModel> getAllClasses();

    void deleteClass(Long id);
}

    
}
