package repository;

import dto.CardDto;
import entity.Card;

import java.sql.Connection;
import java.util.List;

public interface ICardRepository {
    List<CardDto> findAll();
    boolean save(Connection conn, Card card);
    boolean existsByStudentAndBook(int studentId, int bookId);
    boolean existsByCardCode(String cardCode);
    boolean deleteById(Connection conn, int cardId);
}
