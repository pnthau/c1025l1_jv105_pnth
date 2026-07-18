package service;

import dto.CardDto;
import entity.Card;
import entity.Student;

import java.util.List;

public interface ICardService {
    List<CardDto> findAll();

    boolean save(Card card);

    boolean existsByStudentAndBook(int studentId, int bookId);

    boolean existsByCardCode(String cardCode);

    boolean returnBook(int cardId, int bookId);
}
