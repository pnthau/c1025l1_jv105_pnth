package com.example.blog.service;


import com.example.blog.entity.Blog;
import com.example.blog.entity.Category;
import com.example.blog.repository.IBlogRepository;
import com.example.blog.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ICategoryRepository iCategoryRepository;

    public List<Category> getCategories(){
        return iCategoryRepository.findAll();
    }
}
