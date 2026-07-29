package com.example.manager_customer.controller;

import com.example.manager_customer.entity.Customer;
import com.example.manager_customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customers")
    public String showList(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers); // Ném dữ liệu sang HTML
        return "customers/list"; // Chỉ cần trả về tên file HTML là xong!
    }

    @GetMapping("/customers/detail")
    public String showDetail(Model model,@RequestParam(name = "id") int customerId) {
        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        return "customers/info";
    }
}