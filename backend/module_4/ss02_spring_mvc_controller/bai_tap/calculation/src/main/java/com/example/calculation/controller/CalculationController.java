package com.example.calculation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("calculation")
public class CalculationController {
    public record Expression(int a, int b, String operation){
        public int getA() { return a(); }
        public int getB() { return b(); }
        public String getOperation() { return operation(); }
    };

    @GetMapping
    public String calculation()
    {
        return "/calculation";
    }

    @PostMapping
    public String handleCalculatedExpression(@ModelAttribute("expression") Expression expression, RedirectAttributes redirectAttributes, Model model){
        switch (expression.operation())
        {
            case "+":
                redirectAttributes.addFlashAttribute("result", expression.a + expression.b);
                break;
            case "-":
                redirectAttributes.addFlashAttribute("result", expression.a - expression.b);
                break;
            case "*":
                redirectAttributes.addFlashAttribute("result", expression.a * expression.b);
                break;
            case "/":
                if(expression.b == 0)
                {
                    model.addAttribute("errorMsg", "divide by 0");
                    model.addAttribute("expression",expression);
                    return "calculation";
                }
                redirectAttributes.addFlashAttribute("result", expression.a / expression.b);
                break;
        }

        return "redirect:/calculation";
    }
}
