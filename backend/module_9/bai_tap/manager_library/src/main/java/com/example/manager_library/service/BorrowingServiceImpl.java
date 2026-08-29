package com.example.manager_library.service;

import com.example.manager_library.entity.Book;
import com.example.manager_library.entity.Borrowing;
import com.example.manager_library.entity.User;
import com.example.manager_library.repository.IBorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
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
    @Override
    public boolean confirmBorrowing(int bookId, int userId, String borrowCode) {
        try {
            // 1. Lưu phiếu mượn
            Borrowing borrowing = Borrowing.builder()
                    .book(Book.builder().id(bookId).build())
                    .user(User.builder().id(userId).build())
                    .borrow_id(borrowCode)
                    .status("BORROWED")
                    .build();
            borrowingRepository.save(borrowing);

            // 2. Trừ số lượng sách đi 1
            // (Phải dùng câu lệnh query update hoặc lấy book ra sửa. 
            // Nếu có hàm ở BookService thì gọi sang, ở đây giả lập đơn giản)
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

    private String generateDigits(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000,100000));
    }
}
