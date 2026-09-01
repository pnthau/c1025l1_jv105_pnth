package com.example.manager_library.service;

import com.example.manager_library.entity.Book;
import com.example.manager_library.entity.Borrowing;
import com.example.manager_library.entity.User;
import com.example.manager_library.repository.IBorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements IBorrowingService{
    private final IBorrowingRepository borrowingRepository;
    @Override
    public Page<Borrowing> getBorrowingPage(Pageable pageable) {
        return borrowingRepository.findAll(pageable);
    }

    public String createBorrowing(Book book) {
        // Chỉ sinh mã ngẫu nhiên và kiểm tra không trùng lặp, KHÔNG LƯU DB Ở ĐÂY
        boolean flag = true;
        String borrowCode = "";
        do {
            borrowCode = generateDigits();
            flag = !borrowingRepository.existsBorrowingByBorrowId(borrowCode);
        } while (!flag);

        return borrowCode;
    }

    @Transactional
    public boolean confirmBorrowing(int bookId, int userId, String borrowCode) {
        try {
            Borrowing borrowing = Borrowing.builder()
                    .book(Book.builder().id(bookId).build())
                    .user(User.builder().id(1).build())
                    .borrowCode(borrowCode)
                    .status("BORROWED")
                    .build();
            borrowingRepository.save(borrowing);
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
    public boolean returnBook(int borrowingId, String borrowCode) {
        Borrowing borrowing = getBorrowing(borrowingId);
        if (borrowing != null && borrowCode.equals(borrowing.getBorrowCode())) {
            // Optional: borrowing.setStatus("RETURNED"); borrowingRepository.save(borrowing);
            // Implement any additional logic for returning a book here
            borrowingRepository.delete(borrowing); // Or change status based on your business logic
            return true;
        }
        return false;
    }

    private String generateDigits(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000,100000));
    }
}
