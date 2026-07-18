package repository;

import entity.Book;

import java.sql.Connection;
import java.util.List;

public interface IBookRepository {
    List<Book> findAll();

    Book findById(int id);

    boolean updateQuantity(Connection conn, int id);

    boolean increaseQuantity(Connection conn, int id);
}
