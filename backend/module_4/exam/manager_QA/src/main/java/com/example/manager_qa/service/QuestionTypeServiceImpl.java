package com.example.manager_qa.service;

import com.example.manager_qa.entity.QuestionType;
import com.example.manager_qa.repository.IQuestionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionTypeServiceImpl implements IQuestionTypeService {

    private final IQuestionTypeRepository questionTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<QuestionType> findAll() {
        return questionTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionType> findById(Integer id) {
        return questionTypeRepository.findById(id);
    }

    @Override
    public QuestionType save(QuestionType questionType) {
        return questionTypeRepository.save(questionType);
    }
}
