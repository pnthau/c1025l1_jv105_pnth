package com.example.manager_library.service;

import com.example.manager_library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
    Page<Book> getAllBook(Pageable pageable);
    Book getBook(int id);
    Book save(Book book);
}
