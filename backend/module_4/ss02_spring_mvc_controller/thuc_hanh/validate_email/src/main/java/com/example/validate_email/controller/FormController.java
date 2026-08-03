package com.example.validate_email.controller;

import com.example.validate_email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/form")
public class FormController {
    private EmailService emailService = null;

    FormController(EmailService emailService){
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String checkEmail(){
        return "/email";
    }

    @PostMapping("/email")
    public String handleValidatedEmail(@RequestParam(value = "email") String email
    , RedirectAttributes redirectAttributes, Model model){
        if(email.isEmpty())
        {
            model.addAttribute("errorMsg"
                    , "Email is not emptied");
            return "email";
        }
        if(!emailService.checkEmail(email))
        {
            model.addAttribute("errorMsg"
                    , "Email is not validated");
            return "email";
        }

        redirectAttributes.addFlashAttribute("msg"
                , "Email is validated");
        return "redirect:/form/";
    }

}
