package com.example.manager_library.controller;

import com.example.manager_library.entity.Book;
import com.example.manager_library.entity.Borrowing;
import com.example.manager_library.service.IBookService;
import com.example.manager_library.service.IBorrowingService;
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
    private final IBorrowingService borrowingService;

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

    @GetMapping("/create/{id}")
    public String handleBorrowedBook(@PathVariable("id") int id,
                                     Model model) {
        Book book = bookService.getBook(id);
        String borrowCode = borrowingService.createBorrowing(book);
        model.addAttribute("book", book);
        model.addAttribute("borrowCode", borrowCode);
        return "books/confirm-borrow";
    }

    @GetMapping("/borrowing-list")
    public String showAllBorrowing(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            Model model) {
        Page<Borrowing> borrowingPage = borrowingService.getBorrowingPage(pageable);
        model.addAttribute("borrowingPage", borrowingPage);
        return "books/borrowing-list";
    }

    @GetMapping("/book-return")
    public String showFormConfirmReturnBook(@RequestParam("id") int id, Model model) {
        model.addAttribute("id", id);
        return "books/confirm-return";
    }

    @PostMapping("/confirm-return")
    public String handleConfirmReturnBook(@RequestParam("id") int id,
                                          @RequestParam("borrowCode") String borrowCode,
                                          Model model) {
        Borrowing borrowing = borrowingService.getBorrowing(id);
        if (borrowing != null && borrowCode.equals(borrowing.getBorrow_id())) {
            return "redirect:/books/borrowing-list";
        }
        model.addAttribute("id", id);
        model.addAttribute("error", "Mã mượn sách không chính xác!");
        return "books/confirm-return";
    }
}
