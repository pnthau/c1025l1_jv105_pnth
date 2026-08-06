package com.example.setting_languages.controller;

import com.example.setting_languages.model.MailSettings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/setting/language")
public class SettingLanguageController {
    private MailSettings currentSettings = new MailSettings("English", 25, false, "Thor King");

    @GetMapping("")
    public String showForm(Model model){
        model.addAttribute("mailSettings", currentSettings);
        return "setting_languages";
    }

    @PostMapping("/update")
    public String handleUpdateLanguage(@ModelAttribute("mailSettings") MailSettings mailSettings){
        this.currentSettings = mailSettings;
        return "redirect:/setting/language";
    }
}
