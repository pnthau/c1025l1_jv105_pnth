package com.example.manager_qa.repository;

import com.example.manager_qa.entity.Question;
import com.example.manager_qa.entity.QuestionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "SELECT q FROM Question q " +
            "WHERE (:title IS NULL OR :title = '' OR LOWER(q.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "  AND (:content IS NULL OR :content = '' OR LOWER(q.content) LIKE LOWER(CONCAT('%', :content, '%'))) " +
            "  AND (:status IS NULL OR q.status = :status) " +
            "  AND (:typeId IS NULL OR q.questionType.id = :typeId) " +
            "ORDER BY CASE WHEN q.status = com.example.manager_qa.entity.QuestionStatus.WAITING THEN 0 ELSE 1 END ASC, " +
            "q.date DESC, q.id DESC",
            countQuery = "SELECT COUNT(q) FROM Question q " +
                    "WHERE (:title IS NULL OR :title = '' OR LOWER(q.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
                    "  AND (:content IS NULL OR :content = '' OR LOWER(q.content) LIKE LOWER(CONCAT('%', :content, '%'))) " +
                    "  AND (:status IS NULL OR q.status = :status) " +
                    "  AND (:typeId IS NULL OR q.questionType.id = :typeId)")
    Page<Question> searchAndSortQuestions(
            @Param("title") String title,
            @Param("content") String content,
            @Param("status") QuestionStatus status,
            @Param("typeId") Integer typeId,
            Pageable pageable
    );
}
