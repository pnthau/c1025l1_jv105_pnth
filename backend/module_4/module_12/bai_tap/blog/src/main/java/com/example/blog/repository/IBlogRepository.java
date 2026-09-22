package com.example.blog.repository;

import com.example.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBlogRepository extends JpaRepository<Blog, Integer> {
    @Query("select b from Blog b where b.title like %:search% " +
            "and (:categoryId is null or b.category.id = :categoryId)")
    Page<Blog> findAll(@Param("categoryId") Integer categoryId,
                       @Param("search") String search,
                       Pageable pageable);
}
