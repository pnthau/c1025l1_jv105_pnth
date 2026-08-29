package com.example.cart_demo.controller;

import com.example.cart_demo.dto.CartDTO;
import com.example.cart_demo.model.Product;
import com.example.cart_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;
    private final CartDTO cartDTO;

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id) {
        cartDTO.addProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/cart")
    public String displayCart(Model model) {
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
        model.addAttribute("cartDisplay", cartDisplay);
        model.addAttribute("totalPayment", totalPayment);
        return "cart";
    }

    @GetMapping("/cart/decrease/{id}")
    public String decreaseQuantity(@PathVariable Long id) {
        cartDTO.decreaseProduct(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/increase/{id}")
    public String increaseQuantity(@PathVariable Long id) {
        cartDTO.addProduct(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeCartItem(@PathVariable Long id) {
        cartDTO.removeProduct(id);
        return "redirect:/cart";
    }
}
