package com.example.manager_library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "borrowings")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "borrow_code")
    private String borrowCode;

    @Column(nullable = true)
    private String status;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
