package com.example.manager_qa.controller;

import com.example.manager_qa.dto.QuestionRequestDTO;
import com.example.manager_qa.entity.Question;
import com.example.manager_qa.entity.QuestionStatus;
import com.example.manager_qa.entity.QuestionType;
import com.example.manager_qa.service.IQuestionService;
import com.example.manager_qa.service.IQuestionTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping({"/", "/questions"})
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;
    private final IQuestionTypeService questionTypeService;

    @ModelAttribute("questionTypes")
    public List<QuestionType> populateQuestionTypes() {
        return questionTypeService.findAll();
    }

    @ModelAttribute("statuses")
    public QuestionStatus[] populateStatuses() {
        return QuestionStatus.values();
    }

    @GetMapping
    public String listQuestions(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "content", required = false) String content,
            @RequestParam(name = "status", required = false) QuestionStatus status,
            @RequestParam(name = "typeId", required = false) Integer typeId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            Model model
    ) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Question> questionsPage = questionService.searchAndSortQuestions(title, content, status, typeId, pageable);

        if (questionsPage.getTotalPages() > 0 && page >= questionsPage.getTotalPages()) {
            page = questionsPage.getTotalPages() - 1;
            pageable = PageRequest.of(page, size);
            questionsPage = questionService.searchAndSortQuestions(title, content, status, typeId, pageable);
        }

        model.addAttribute("questionsPage", questionsPage);
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("selectedTypeId", typeId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", questionsPage.getTotalPages());

        return "question/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        QuestionRequestDTO dto = QuestionRequestDTO.builder()
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build();
        model.addAttribute("questionDTO", dto);
        return "question/create";
    }

    @PostMapping("/create")
    public String createQuestion(
            @Valid @ModelAttribute("questionDTO") QuestionRequestDTO dto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "question/create";
        }

        try {
            questionService.create(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm câu hỏi mới thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm câu hỏi: " + e.getMessage());
        }

        return "redirect:/questions";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        return questionService.findById(id).map(question -> {
            QuestionRequestDTO dto = QuestionRequestDTO.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .content(question.getContent())
                    .answer(question.getAnswer())
                    .questionTypeId(question.getQuestionType() != null ? question.getQuestionType().getId() : null)
                    .date(question.getDate())
                    .status(question.getStatus())
                    .build();
            model.addAttribute("questionDTO", dto);
            model.addAttribute("questionId", id);
            return "question/edit";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy câu hỏi với ID: " + id);
            return "redirect:/questions";
        });
    }

    @PostMapping("/{id}/edit")
    public String updateQuestion(
            @PathVariable("id") Integer id,
            @Valid @ModelAttribute("questionDTO") QuestionRequestDTO dto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("questionId", id);
            return "question/edit";
        }

        try {
            questionService.update(id, dto);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật câu hỏi thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật: " + e.getMessage());
        }

        return "redirect:/questions";
    }

    @GetMapping("/{id}")
    public String viewDetail(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        return questionService.findById(id).map(question -> {
            model.addAttribute("question", question);
            return "question/detail";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy câu hỏi với ID: " + id);
            return "redirect:/questions";
        });
    }

    @PostMapping("/{id}/delete")
    public String deleteQuestion(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            questionService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa câu hỏi thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa câu hỏi: " + e.getMessage());
        }
        return "redirect:/questions";
    }
}
