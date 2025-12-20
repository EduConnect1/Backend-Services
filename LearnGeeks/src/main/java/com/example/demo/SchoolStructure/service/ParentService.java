package com.example.demo.SchoolStructure.service;


import com.school.automation.model.Parent;

import java.util.List;

public interface ParentService {

    Parent createParent(Long studentId, Parent parent);

    Parent getParentById(Long id);

    List<Parent> getParentsByStudent(Long studentId);

    void deleteParent(Long id);
}
