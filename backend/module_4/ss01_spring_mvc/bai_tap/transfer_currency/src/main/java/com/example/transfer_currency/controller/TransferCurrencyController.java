package com.example.transfer_currency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransferCurrencyController {
    public record TransferRequest(double usdAmount, double exchangeRate) {};
    @GetMapping("/transfer")
    public String transferCurrency(Model model){
        return "transfer_currency";
    }
    @PostMapping("/transfer")
    public String calculate(Model model,@ModelAttribute TransferRequest request)
    {
        double vnd = request.usdAmount() * request.exchangeRate();
        model.addAttribute("result", vnd);
        return "transfer_currency";
    }
}
