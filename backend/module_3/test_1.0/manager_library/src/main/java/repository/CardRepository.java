package repository;

import dto.CardDto;
import entity.Card;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardRepository extends BaseRepository implements ICardRepository {
    private static final String SELECT_ALL_CARDS = "select c.id, c.card_code, c.book_id, b.name as book_name, b.author, s.name as student_name, s.class, c.start_date, c.end_date from membership_cards as c "
            +
            "join students as s on s.id= c.student_id " +
            "join books as b on c.book_id = b.id  ";
    private static final String INSERT_CARD = "insert into membership_cards(card_code,book_id,student_id,status,start_date,end_date) "
            +
            "values(?,?,?,?,?,?)";
    private static final String DELETE_CARD = "delete from membership_cards where id = ?";

    private static final String EXISTS_BY_STUDENT_AND_BOOK = "SELECT count(*) FROM membership_cards WHERE student_id = ? AND book_id = ?";

    private static final String EXISTS_BY_CARD_CODE = "SELECT count(*) FROM membership_cards WHERE card_code = ?";

    @Override
    public List<CardDto> findAll() {
        List<CardDto> cardList = new ArrayList<>();
        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_ALL_CARDS);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String cardCode = rs.getString("card_code");
                int bookId = rs.getInt("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                String studentName = rs.getString("student_name");
                String className = rs.getString("class");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");

                CardDto card = new CardDto(id, cardCode, bookId, bookName, author, studentName, className, null,
                        startDate, endDate);
                cardList.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardList;
    }

    @Override
    public boolean save(Connection conn, Card card) {
        int rowEffect = 0;
        try (
                PreparedStatement ps = conn.prepareStatement(INSERT_CARD);) {
            ps.setString(1, card.getCardCode());
            ps.setInt(2, card.getBookId());
            ps.setInt(3, card.getStudentId());
            ps.setBoolean(4, true);
            ps.setDate(5, card.getStartDate() != null ? Date.valueOf(card.getStartDate()) : null);
            ps.setDate(6, card.getEndDate() != null ? Date.valueOf(card.getEndDate()) : null);

            rowEffect = ps.executeUpdate();
            return rowEffect > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsByStudentAndBook(int studentId, int bookId) {
        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(EXISTS_BY_STUDENT_AND_BOOK)) {
            ps.setInt(1, studentId);
            ps.setInt(2, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    
    }

    @Override
    public boolean existsByCardCode(String cardCode) {
        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(EXISTS_BY_CARD_CODE)) {
            ps.setString(1, cardCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(Connection conn, int cardId) {
        try (PreparedStatement ps = conn.prepareStatement(DELETE_CARD)) {
            ps.setInt(1, cardId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
