package com.example.blog.service;

import com.example.blog.entity.Blog;
import com.example.blog.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlockRepository blockRepository;

    public List<Blog> getBlogs(){
      List<Blog> blogList =  blockRepository.findAll();
      return blogList;
    }

    public Blog getBlogById(int id){
        return blockRepository.findById(id);
    }

    public boolean save(Blog blog)
    {
        return blockRepository.save(blog);
    }


    public boolean delete(int id)
    {
        return blockRepository.delete(id);
    }
}
