package com.example.manager_qa.service;

import com.example.manager_qa.dto.QuestionRequestDTO;
import com.example.manager_qa.entity.Question;
import com.example.manager_qa.entity.QuestionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IQuestionService {
    Page<Question> searchAndSortQuestions(String title, String content, QuestionStatus status, Pageable pageable);
    Page<Question> searchAndSortQuestions(String title, String content, QuestionStatus status, Integer typeId, Pageable pageable);
    Optional<Question> findById(Integer id);
    Question create(QuestionRequestDTO dto);
    Question update(Integer id, QuestionRequestDTO dto);
    void deleteById(Integer id);
}
