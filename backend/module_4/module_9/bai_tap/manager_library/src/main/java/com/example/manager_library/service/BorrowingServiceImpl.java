package com.example.manager_library.service;

import com.example.manager_library.dto.ConfirmBorrowRequest;
import com.example.manager_library.dto.ReturnBorrowRequest;
import com.example.manager_library.entity.Book;
import com.example.manager_library.entity.Borrowing;
import com.example.manager_library.entity.User;
import com.example.manager_library.repository.IBookRepository;
import com.example.manager_library.repository.IBorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements IBorrowingService{
    private final IBorrowingRepository borrowingRepository;
    private final IBookRepository bookRepository;
    @Override
    public Page<Borrowing> getBorrowingPage(Pageable pageable) {
        return borrowingRepository.findAll(pageable);
    }

    @Transactional
    public boolean confirmBorrowing(ConfirmBorrowRequest request) {
        try {
            Borrowing borrowing = borrowingRepository.findByBorrowCode(request.getBorrowCode());
            if(borrowing != null && "PENDING".equals(borrowing.getStatus()))
            {
                Book book = borrowing.getBook();
                book.decreaseQuantity();
                bookRepository.save(book);

                borrowing.setStatus("BORROWED");
                borrowingRepository.save(borrowing);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Borrowing getBorrowing(int id) {
        return borrowingRepository.findBorrowingById(id);
    }

    @Override
    @Transactional
    public boolean returnBook(ReturnBorrowRequest request) {
        Borrowing borrowing = getBorrowing(request.getBorrowId());
        if(borrowing != null && "BORROWED".equals(borrowing.getStatus()))
        {
            Book book = borrowing.getBook();
            book.increaseQuantity();
            bookRepository.save(book);

            borrowing.setStatus("RETURN");
            borrowingRepository.save(borrowing);
            return true;
        }
        return false;
    }

    @Override
    public String createPendingBorrowing(int bookId, int userId) {
        String borrowCode;
        do {
            borrowCode = generateDigits();
        } while (borrowingRepository.existsBorrowingByBorrowCode(borrowCode));
        Borrowing pendingBorrowing = Borrowing.builder()
                .book(Book.builder().id(bookId).build())
                .user(User.builder().id(userId).build())
                .borrowCode(borrowCode)
                .status("PENDING")
                .build();
        borrowingRepository.save(pendingBorrowing);
        return borrowCode;
    }

    @Override
    public Borrowing findByBorrowCode(String borrowCode) {
        return borrowingRepository.findByBorrowCode(borrowCode);
    }

    @Override
    public Page<Borrowing> findAllBorrowed(Pageable pageable) {
        return borrowingRepository.findAllBorrowed(pageable);
    }

    @Override
    public boolean findByBookIdAndStatusBorrowed(int bookId, int userId) {
        return borrowingRepository.findByBookIdAndStatusBorrowed(bookId, userId);
    }

    private String generateDigits(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000,100000));
    }
}
