package com.example.manager_library.service;

import com.example.manager_library.dto.ConfirmBorrowRequest;
import com.example.manager_library.dto.ReturnBorrowRequest;
import com.example.manager_library.entity.Book;
import com.example.manager_library.entity.Borrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBorrowingService {
    Page<Borrowing> getBorrowingPage(Pageable pageable);
    Borrowing getBorrowing(int id);
    boolean confirmBorrowing(ConfirmBorrowRequest confirmBorrowRequest);
    boolean returnBook(ReturnBorrowRequest request);
    public String createPendingBorrowing(int bookId, int userId);
    Borrowing findByBorrowCode(String borrowCode);
    Page<Borrowing> findAllBorrowed(Pageable pageable);
    boolean findByBookIdAndStatusBorrowed(int bookId, int userId);
}
