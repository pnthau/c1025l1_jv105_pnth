package com.example.blog.controller.api;

import com.example.blog.entity.Blog;
import com.example.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/blogs")
@RequiredArgsConstructor
public class BlockAPIController {
    private final BlogService blogService;
    @GetMapping
    public ResponseEntity<Page<Blog>> displayAllBlogs(
            @RequestParam(value = "title", defaultValue = "") String search,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            Pageable pageable){
        Page<Blog> blogPage =  blogService.getBlogs(categoryId,search,pageable);
        return new ResponseEntity<>(blogPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> displayBlogDetail(@PathVariable Integer id)
    {
        Blog blog = blogService.getBlogById(id);
        if(blog == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog,HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Blog> createBlog(@RequestBody Blog blog)
    {
        Blog newBlog = blogService.save(blog);

        if(newBlog != null)
        {
            return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
        }else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> removeBlog(@PathVariable int id)
    {
        Blog blog = blogService.getBlogById(id);
        if(blog == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean isDeleted =  blogService.delete(blog);
        if(isDeleted)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
