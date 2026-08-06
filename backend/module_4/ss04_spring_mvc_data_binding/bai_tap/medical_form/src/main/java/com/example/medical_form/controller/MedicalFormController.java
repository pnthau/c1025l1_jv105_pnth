package com.example.medical_form.controller;

import com.example.medical_form.dto.MedicalDeclarationDTO;
import com.example.medical_form.model.MedicalDeclaration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.beans.BeanUtils;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/medial")
public class MedicalFormController {
    // Lưu tạm vào bộ nhớ (mock database)
    private MedicalDeclaration medicalDeclaration = new MedicalDeclaration();

    @GetMapping
    public String showMedicalForm(Model model){
        // Gửi một đối tượng DTO rỗng sang form để hứng dữ liệu
        model.addAttribute("medicalForm", new MedicalDeclarationDTO());
        return "form";
    }

    @PostMapping("/save")
    public String handleMedicalForm(
            @Valid @ModelAttribute("medicalForm") MedicalDeclarationDTO medicalDeclarationDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){
        
        // Nếu có lỗi validation, quay lại trang form
        if(bindingResult.hasErrors()) {
            return "form";
        }
        BeanUtils.copyProperties(medicalDeclarationDTO, medicalDeclaration);
        
        redirectAttributes.addFlashAttribute("message", "Đã nộp tờ khai thành công!");
        return "redirect:/medial"; // Redirect về trang hiển thị form
    }
}
