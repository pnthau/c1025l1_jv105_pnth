package com.example.manager_library.repository;

import com.example.manager_library.entity.Borrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBorrowingRepository extends JpaRepository<Borrowing, Integer> {
    @Query("select count(b) > 0 from Borrowing b where b.borrowCode = :borrowCode")
    boolean existsBorrowingByBorrowCode(@Param("borrowCode") String borrowCode);

    Borrowing findBorrowingById(int id);

    @Query("select b from Borrowing  b where b.borrowCode = :borrowCode")
    Borrowing findByBorrowCode(String borrowCode);

    @Query("select b from Borrowing b where b.status = 'BORROWED' ")
    Page<Borrowing> findAllBorrowed(Pageable pageable);

    @Query("select count(b) > 0 from Borrowing  b where b.book.id = :bookId and b.user.id = :userId and b.status = 'BORROWED'")
    boolean findByBookIdAndStatusBorrowed(int bookId, int userId);
}
