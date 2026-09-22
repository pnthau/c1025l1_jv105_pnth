package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlockController {
    private final  BlogService service;
    private final CategoryService categoryService;
    @GetMapping
    public String displayAllBlogs(
            @RequestParam(value = "title", defaultValue = "") String search,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            Model model,
             @PageableDefault(size=2, sort = "createAt") Pageable pageable)
    {
        Page<Blog> blogList =  service.getBlogs(categoryId,search,pageable);

        model.addAttribute("blogList", blogList);
        model.addAttribute("titleSearch", search);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categoryList", categoryService.getCategories());
        return "/blog/list";
    }

    @GetMapping("/form")
    public String displayForm(@RequestParam(value = "id", required = false) Integer id, Model model){
        Blog blog = new Blog();
        if(id != null)
        {
            blog =  service.getBlogById(id);
        }

        model.addAttribute("blog", blog);
        return "/blog/form";
    }

    @PostMapping("/save")
    public String handleSave(@ModelAttribute(value = "blog") Blog blog,
                               RedirectAttributes attributes,
                               Model model)
    {
        boolean isNew = blog.getId() == 0;
        Blog savedBlog =  service.save(blog);
        boolean isSuccess = (savedBlog != null);
        String message = "";

        if(isSuccess)
        {
            message = isNew ? "add success" : "update success";
            attributes.addFlashAttribute("msg", message);
            return "redirect:/blogs";
        }
         message = isNew ? "add fail" : "update fail";
        model.addAttribute("errMsg", message);
        return "/blog/form";
    }

    @GetMapping("/delete")
    public String handleDelete( Blog blog,
                             RedirectAttributes attributes,
                             Model model)
    {
        boolean isDeleted = service.delete(blog);
        String message = "";

        if(isDeleted)
        {
            message = "delete success";
            attributes.addFlashAttribute("msg", message);
            return "redirect:/blogs";
        }
        message = "delete fail";
        model.addAttribute("errMsg", message);
        return "/blog/form";
    }

    @GetMapping("detail/{id}")
    public String  showDetailBlog(@PathVariable int id, Model model){
        Blog blog   = service.getBlogById(id);
        model.addAttribute("blog", blog);
        return "/blog/detail";
    }
}
