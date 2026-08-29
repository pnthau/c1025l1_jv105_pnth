package com.example.manager_library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "borrowing",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_book_user", columnNames = {"book_id", "user_id"})
        }
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String borrow_id;

    @Column(nullable = true)
    private String status;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
