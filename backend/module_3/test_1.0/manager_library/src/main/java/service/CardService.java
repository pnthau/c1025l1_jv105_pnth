package service;

import dto.CardDto;
import entity.Card;
import repository.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CardService implements ICardService{
    private final ICardRepository  cardRepository= new CardRepository();
    private final IBookRepository bookRepository= new BookRepository();

    @Override
    public List<CardDto> findAll() {
        return  cardRepository.findAll();
    }

    @Override
    public boolean save(Card card) {
        try (Connection conn = BaseRepository.getConnection()) {

            conn.setAutoCommit(false);

            boolean isCardSaved = cardRepository.save(conn, card);
            boolean isQuantityUpdated = bookRepository.updateQuantity(conn, card.getBookId());

            if (isCardSaved && isQuantityUpdated) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsByStudentAndBook(int studentId, int bookId) {
        return cardRepository.existsByStudentAndBook(studentId, bookId);
    }

    @Override
    public boolean existsByCardCode(String cardCode) {
        return cardRepository.existsByCardCode(cardCode);
    }

    @Override
    public boolean returnBook(int cardId, int bookId) {
        try (Connection conn = BaseRepository.getConnection()) {
            try {
                conn.setAutoCommit(false);

                boolean isCardDeleted = cardRepository.deleteById(conn, cardId);
                boolean isQuantityIncreased = bookRepository.increaseQuantity(conn, bookId);

                if (isCardDeleted && isQuantityIncreased) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
