package com.example.distionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class TranslateController {
    private static Map<String, List<String>> dictionary;
    static{
        dictionary = new TreeMap<>();
        dictionary.put("Application", List.of("Ứng dụng"));
        dictionary.put("Inversion", List.of("Đảo ngược"));
        dictionary.put("Injection", List.of("Tiêm"));
    }
    @GetMapping("/search")
    public String handleTranslate(@RequestParam(name = "word", defaultValue = "") String word, Model model)
    {
        model.addAttribute("searched", true);
        if(!word.isEmpty() && dictionary.containsKey(word))
        {
            model.addAttribute("meaning", dictionary.get(word));
        }
        return "search";
    }

    @GetMapping("/")
    public String homeSearch()
    {
        return "redirect:/search";
    }
}
