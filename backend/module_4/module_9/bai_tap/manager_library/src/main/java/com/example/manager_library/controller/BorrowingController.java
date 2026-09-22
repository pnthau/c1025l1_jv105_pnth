package com.example.manager_library.controller;

import com.example.manager_library.dto.ConfirmBorrowRequest;
import com.example.manager_library.dto.ReturnBorrowRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BorrowingController {

    private final IBookService bookService;
    private final IBorrowingService borrowingService;

    // Chức năng mượn sách
    @GetMapping("/books/create/{id}")
    public String handleBorrowedBook(@PathVariable(name = "id") int bookId,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {
        Book book = bookService.getBook(bookId);
        boolean isBorrowingExits = borrowingService.findByBookIdAndStatusBorrowed(bookId,1);
        if(isBorrowingExits)
        {
            redirectAttributes.addFlashAttribute("msgError", "Bạn đang mượng cuốn sách này vui lòng trả trước khi mượng lại");
            return "redirect:/books";
        }
        String borrowCode = borrowingService.createPendingBorrowing(bookId,1);

        model.addAttribute("book", book);
        model.addAttribute("borrowCode", borrowCode);
        return "books/confirm-borrow";
    }

    @PostMapping("/books/confirm-borrow")
    public String confirmBorrowedBook(ConfirmBorrowRequest request,
                                      Model model)
    {
        boolean isSuccess =  borrowingService.confirmBorrowing(request);
        if(isSuccess)
        {
            return "redirect:/books/borrowing-list";
        }
        model.addAttribute("msgError", "Mượng sách không thành công. vui lòng thử lại");
        return "/books/list";
    }
    // Danh sách mượn
    @GetMapping("/books/borrowing-list")
    public String showAllBorrowing(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            Model model) {
        Page<Borrowing> borrowingPage = borrowingService.findAllBorrowed(pageable);
        model.addAttribute("borrowingPage", borrowingPage);
        return "books/borrowing-list";
    }

    // Form trả sách
    @GetMapping("/books/book-return")
    public String showFormConfirmReturnBook(@RequestParam("id") int id, Model model) {
        model.addAttribute("id", id);
        return "books/confirm-return";
    }

    // Xử lý trả sách
    @PostMapping("/books/confirm-return")
    public String handleConfirmReturnBook(ReturnBorrowRequest request,
                                          Model model) {
        boolean isSuccess = borrowingService.returnBook(request);
        
        if (isSuccess) {
            return "redirect:/books/borrowing-list";
        }
        
        model.addAttribute("id", request.getBorrowId());
        model.addAttribute("error", "Mã mượn sách không chính xác!");
        return "books/confirm-return";
    }
}
