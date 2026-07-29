package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// CHÚ Ý: Chỗ này dùng @Controller (không có chữ Rest) để trả về file giao diện
@Controller
public class WebController {

    @GetMapping("/trang-chu")
    public String hienThiTrangChu(Model model, @RequestParam(defaultValue = "Khách") String ten) {
        
        // Model là một cái giỏ đựng dữ liệu. Bạn bỏ đồ vào giỏ ở đây,
        // rồi quăng cái giỏ này sang cho file HTML (Thymeleaf) nó tự bóc ra hiển thị.
        model.addAttribute("loiChao", "Xin chào " + ten + ", chào mừng đến với Thymeleaf!");
        
        // Lệnh này xúi Spring Boot chạy vào thư mục 'templates', 
        // tìm file có tên 'index.html' rồi vẽ lên cho người dùng xem.
        return "index";
    }
}
