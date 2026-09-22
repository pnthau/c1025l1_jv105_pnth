package com.example.manager_qa.service;

import com.example.manager_qa.dto.QuestionRequestDTO;
import com.example.manager_qa.entity.Question;
import com.example.manager_qa.entity.QuestionStatus;
import com.example.manager_qa.entity.QuestionType;
import com.example.manager_qa.repository.IQuestionRepository;
import com.example.manager_qa.repository.IQuestionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements IQuestionService {

    private final IQuestionRepository questionRepository;
    private final IQuestionTypeRepository questionTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Question> searchAndSortQuestions(String title, String content, QuestionStatus status, Pageable pageable) {
        return searchAndSortQuestions(title, content, status, null, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> searchAndSortQuestions(String title, String content, QuestionStatus status, Integer typeId, Pageable pageable) {
        String searchTitle = (title != null && !title.trim().isEmpty()) ? title.trim() : null;
        String searchContent = (content != null && !content.trim().isEmpty()) ? content.trim() : null;
        return questionRepository.searchAndSortQuestions(searchTitle, searchContent, status, typeId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Question> findById(Integer id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question create(QuestionRequestDTO dto) {
        QuestionType questionType = questionTypeRepository.findById(dto.getQuestionTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Loại câu hỏi không hợp lệ!"));

        LocalDate questionDate = dto.getDate() != null ? dto.getDate() : LocalDate.now();
        QuestionStatus status = dto.getStatus() != null ? dto.getStatus() : QuestionStatus.WAITING;

        Question question = Question.builder()
                .title(dto.getTitle().trim())
                .content(dto.getContent().trim())
                .answer(dto.getAnswer() != null ? dto.getAnswer().trim() : null)
                .questionType(questionType)
                .date(questionDate)
                .status(status)
                .build();

        return questionRepository.save(question);
    }

    @Override
    public Question update(Integer id, QuestionRequestDTO dto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy câu hỏi với ID: " + id));

        QuestionType questionType = questionTypeRepository.findById(dto.getQuestionTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Loại câu hỏi không hợp lệ!"));

        question.setTitle(dto.getTitle().trim());
        question.setContent(dto.getContent().trim());
        question.setAnswer(dto.getAnswer() != null ? dto.getAnswer().trim() : null);
        question.setQuestionType(questionType);
        if (dto.getDate() != null) {
            question.setDate(dto.getDate());
        }
        if (dto.getStatus() != null) {
            question.setStatus(dto.getStatus());
        }

        return questionRepository.save(question);
    }

    @Override
    public void deleteById(Integer id) {
        if (!questionRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy câu hỏi với ID: " + id);
        }
        questionRepository.deleteById(id);
    }
}
