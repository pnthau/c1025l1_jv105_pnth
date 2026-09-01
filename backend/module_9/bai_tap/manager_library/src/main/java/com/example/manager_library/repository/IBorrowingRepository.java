package com.example.manager_library.repository;

import com.example.manager_library.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBorrowingRepository extends JpaRepository<Borrowing, Integer> {
    @Query("select count(b) > 0 from Borrowing b where b.borrow_id = :borrowId")
    boolean existsBorrowingByBorrowId(@Param("borrowId") String borrowId);

    Borrowing findBorrowingById(int id);
}
