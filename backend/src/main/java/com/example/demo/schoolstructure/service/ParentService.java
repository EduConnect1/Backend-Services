package com.example.demo.schoolstructure.service;

import com.example.demo.schoolstructure.model.Parent;
import java.util.List;

public interface ParentService {

    Parent createParent(Long studentId, Parent parent);

    Parent getParentById(Long id);

    List<Parent> getParentsByStudent(Long studentId);

    List<Parent> getAllParents();

    void deleteParent(Long id);
}
