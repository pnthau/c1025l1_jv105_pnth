package com.example.manager_library.service;

import com.example.manager_library.entity.Book;
import com.example.manager_library.entity.Borrowing;
import com.example.manager_library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBorrowingService {
    Page<Borrowing> getBorrowingPage(Pageable pageable);
    String createBorrowing(Book book);
    Borrowing getBorrowing(int id);
}
