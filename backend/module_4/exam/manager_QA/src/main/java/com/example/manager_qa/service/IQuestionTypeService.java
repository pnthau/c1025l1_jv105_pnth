package com.example.manager_qa.service;

import com.example.manager_qa.entity.QuestionType;

import java.util.List;
import java.util.Optional;

public interface IQuestionTypeService {
    List<QuestionType> findAll();
    Optional<QuestionType> findById(Integer id);
    QuestionType save(QuestionType questionType);
}
