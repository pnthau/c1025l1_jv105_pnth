package com.example.manager_library.controller;

import com.example.manager_library.entity.Book;
import com.example.manager_library.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;

    @GetMapping({"", "/"})
    public String showAllBook(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            Model model) {
        Page<Book> bookPage = bookService.getAllBook(pageable);
        model.addAttribute("bookPage", bookPage);
        return "books/list";
    }

    @GetMapping("/detail/{id}")
    public String showBook(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("book", bookService.getBook(id));
        return "books/detail";
    }

}
