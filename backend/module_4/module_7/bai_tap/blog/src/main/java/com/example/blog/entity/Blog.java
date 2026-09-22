package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "datetime(6) default current_timestamp(6)")
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", columnDefinition = "int default 1")
    private Category category;
}
