package com.example.cart_demo.controller;

import com.example.cart_demo.dto.CartDTO;
import com.example.cart_demo.model.Order;
import com.example.cart_demo.model.Product;
import com.example.cart_demo.service.OrderService;
import com.example.cart_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;
    private final CartDTO cartDTO;

    @GetMapping("/checkout")
    public String displayCheckout(Model model) {
        Map<Product, Integer> cartDisplay = new LinkedHashMap<>();
        double totalPayment = 0;

        Set<Long> listIds = cartDTO.getProducts().keySet();
        if (!listIds.isEmpty()) {
            List<Product> productList = productService.findAllById(listIds);
            for (Product p : productList) {
                Integer quantity = cartDTO.getProducts().get(p.getId());
                cartDisplay.put(p, quantity);
                totalPayment += p.getPrice() * quantity;
            }
        }
        
        if (cartDisplay.isEmpty()) {
            return "redirect:/products";
        }

        model.addAttribute("cartDisplay", cartDisplay);
        model.addAttribute("totalPayment", totalPayment);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@RequestParam("customerName") String customerName) {
        // Tạo Đơn hàng mới
        Order order = new Order();
        order.setCustomerName(customerName);

        
        Map<Product, Integer> cartItems = new LinkedHashMap<>();
        Set<Long> listIds = cartDTO.getProducts().keySet();
        if (!listIds.isEmpty()) {
            List<Product> productList = productService.findAllById(listIds);
            for (Product p : productList) {
                cartItems.put(p, cartDTO.getProducts().get(p.getId()));
            }
        }
        
        orderService.placeOrder(order, cartItems);

        // Xóa trắng giỏ hàng
        cartDTO.getProducts().clear();

        return "redirect:/payment-success";
    }
    
    @GetMapping("/payment-success")
    public String paymentSuccess() {
        return "success";
    }
}
