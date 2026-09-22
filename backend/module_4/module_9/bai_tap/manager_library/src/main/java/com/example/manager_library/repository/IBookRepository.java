package com.example.manager_library.repository;

import com.example.manager_library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book,Integer> {
    Book findBookById(int id);
}
