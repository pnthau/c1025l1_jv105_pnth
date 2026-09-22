package com.example.blog.service;

import com.example.blog.entity.Blog;
import com.example.blog.repository.IBlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final IBlogRepository blockRepository;

    public Page<Blog> getBlogs(Integer categoryId,String search,Pageable pageable){
      return blockRepository.findAll(categoryId , search,pageable);
    }

    public Blog getBlogById(int id){
        return blockRepository.findById(id).get();
    }

    public boolean save(Blog blog)
    {
        try{
            blockRepository.save(blog);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean delete(Blog blog)
    {
        blockRepository.delete(blog);
        return true;
    }
}
