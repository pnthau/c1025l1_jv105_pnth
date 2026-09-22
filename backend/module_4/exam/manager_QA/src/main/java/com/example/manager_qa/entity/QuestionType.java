package com.example.manager_qa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;
}
