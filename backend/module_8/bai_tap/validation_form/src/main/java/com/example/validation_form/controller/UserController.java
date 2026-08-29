package com.example.validation_form.controller;

import com.example.validation_form.dto.UserRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.validation_form.service.IUserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    @GetMapping
    public String showForm(Model model){
        model.addAttribute("userRequestDTO", new UserRequestDTO());
        return "/users/index";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute UserRequestDTO userRequestDTO,
                             BindingResult  bindingResult,
                             Model model)
    {
        if(userService.isExitEmail(userRequestDTO.getEmail()))
        {
            bindingResult.rejectValue("email", "error.email", "email đã tồn tại rồi");
        }
        if(bindingResult.hasErrors())
        {
            return "/users/index";
        }
        userService.save(userRequestDTO);
        return "redirect:/users/result";
    }

    @GetMapping("/result")
    public  String showSuccessPage(){
        return "/users/result";
    }
}
