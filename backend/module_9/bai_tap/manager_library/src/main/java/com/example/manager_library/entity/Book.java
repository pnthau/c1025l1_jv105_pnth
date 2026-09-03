package com.example.manager_library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "books")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int remaining;


    public void decreaseQuantity(){
        if (this.remaining <= 0) {
            throw new RuntimeException("Sách ?ã h?t, không th? m??n thêm!");
        }
        this.remaining -= 1;
    }

    public void increaseQuantity(){
        this.remaining += 1;
    }
}
