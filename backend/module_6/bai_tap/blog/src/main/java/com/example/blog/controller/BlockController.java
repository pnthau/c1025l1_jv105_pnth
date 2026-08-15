package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public String displayAllBlogs(Model model){
        List<Blog> blogList =  service.getBlogs();
        model.addAttribute("blogs", blogList);
        return "/list";
    }

    @GetMapping("/form")
    public String displayForm(@RequestParam(value = "id", required = false) Integer id, Model model){
        Blog blog = new Blog();
        if(id != null)
        {
            blog =  service.getBlogById(id);
        }

        model.addAttribute("blog", blog);
        return "/form";
    }

    @PostMapping("/save")
    public String handleSave(@ModelAttribute(value = "blog") Blog blog,
                               RedirectAttributes attributes,
                               Model model)
    {
        boolean isNew = blog.getId() == 0;
        boolean isSuccess=  service.save(blog);
        String message = "";
        if(isSuccess)
        {
            message = isNew ? "add success" : "update success";
            attributes.addFlashAttribute("msg", message);
            return "redirect:/blogs";
        }
         message = isNew ? "add fail" : "update fail";
        model.addAttribute("errMsg", message);
        return "/form";
    }

    @GetMapping("/delete")
    public String handleSave( @RequestParam(name = "id") int id,
                             RedirectAttributes attributes,
                             Model model)
    {
        boolean isSuccess = service.delete(id);
        String message = "";
        if(isSuccess)
        {
            message = "delete success";
            attributes.addFlashAttribute("msg", message);
            return "redirect:/blogs";
        }
        message = "delete fail";
        model.addAttribute("errMsg", message);
        return "/form";
    }

}
