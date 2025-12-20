package com.example.demo.SchoolStructure.service;

import com.example.demo.SchoolStructure.Model.Subject;
import com.example.demo.SchoolStructure.Model.Teacher;
import com.example.demo.SchoolStructure.repository.SubjectRepository;
import com.example.demo.SchoolStructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher assignSubjects(Long teacherId, Set<Long> subjectIds) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Set<Subject> subjects = new HashSet<>(subjectRepository.findAllById(subjectIds));
        teacher.setSubjects(subjects);

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
