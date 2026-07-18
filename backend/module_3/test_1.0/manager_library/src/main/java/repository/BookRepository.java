package repository;

import entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository extends BaseRepository implements IBookRepository {
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM books;";
    private static final String SELECT_BOOK = "SELECT * FROM books where id = ?;";
    private static final String UPDATE_QUANTITY_BOOK = "update books set quantity = quantity - 1 where id = ?";
    private static final String INCREASE_QUANTITY_BOOK = "update books set quantity = quantity + 1 where id = ?";
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BOOKS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String bookCode = rs.getString("book_code");
                String name = rs.getString("name");
                String author = rs.getString("author");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");

                bookList.add(new Book(id, bookCode ,name, author, description, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public Book findById(int id) {
        Book book = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BOOK)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery();) {
                if (resultSet.next()) {
                    String bookCode = resultSet.getString("book_code");
                    String name = resultSet.getString("name");
                    String author = resultSet.getString("author");
                    String description = resultSet.getString("description");
                    int quantity = resultSet.getInt("quantity");

                    book = new Book(id, bookCode,name, author, description, quantity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean updateQuantity(Connection conn,int id) {
        int rowEffect = 0;
        try(
                PreparedStatement ps = conn.prepareStatement(UPDATE_QUANTITY_BOOK);
        ){
            ps.setInt(1, id);
            rowEffect = ps.executeUpdate();
            return rowEffect > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean increaseQuantity(Connection conn, int id) {
        int rowEffect = 0;
        try (PreparedStatement ps = conn.prepareStatement(INCREASE_QUANTITY_BOOK)) {
            ps.setInt(1, id);
            rowEffect = ps.executeUpdate();
            return rowEffect > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
