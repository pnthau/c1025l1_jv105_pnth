package service;

import entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();

    Book findById(int id);
}
