package com.example.manager_customer.controller;

import com.example.manager_customer.entity.Customer;
import com.example.manager_customer.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@EnableWebMvc
@RequestMapping("/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private CustomerController(ICustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping("")
    public String displayCustomers(Model model){
        model.addAttribute("customerList", this.customerService.findAll());
        return "/customers/list";
    }

    @GetMapping("/create")
    public String displayCreateCustomerForm(){
        return "/customers/create";
    }

    @PostMapping("/create")
    public String handleCreateCustomerForm(@ModelAttribute Customer customer,
                                           RedirectAttributes redirectAttributes,
                                           Model model){
        boolean isCreated = customerService.save(customer);
        if(!isCreated)
        {
            model.addAttribute("errorMsg", "failure");
            return "customers/create";
        }
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/customers";
    }

    @GetMapping("/show/{id}")
    public String displayDetailCustomer(@PathVariable(value = "id") int id, Model model)
    {
        model.addAttribute("customer", customerService.findById(id)) ;
        return "/customers/detail";
    }
}
