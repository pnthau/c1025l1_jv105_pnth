package com.example.manager_qa.repository;

import com.example.manager_qa.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IQuestionTypeRepository extends JpaRepository<QuestionType, Integer> {
    Optional<QuestionType> findByName(String name);
}
