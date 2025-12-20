package com.example.demo.SchoolStructure.service;


import com.example.demo.SchoolStructure.Model.ParentModel;

import java.util.List;

public interface ParentService {

    ParentModel createParent(Long studentId, ParentModel parent);

    ParentModel getParentById(Long id);

    List<ParentModel> getParentsByStudent(Long studentId);

    void deleteParent(Long id);
}
