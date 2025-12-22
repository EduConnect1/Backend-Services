package com.example.demo.OnlineExam.repository;


import com.example.demo.OnlineExam.Model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByQuestionId(Long questionId);
}