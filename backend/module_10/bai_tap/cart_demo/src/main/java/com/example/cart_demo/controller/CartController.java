package com.example.cart_demo.controller;

import com.example.cart_demo.dto.CartDTO;
import com.example.cart_demo.model.Product;
import com.example.cart_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;
    private final CartDTO cartDTO;

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent())
        {
            int currentCartQuantity = cartDTO.getProducts().getOrDefault(id, 0);
            int currentQuantity = product.get().getQuantity();
            if(currentQuantity > currentCartQuantity)
            {
                cartDTO.addProduct(id);
            }
        }
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
    public String decreaseQuantity(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        int currentCartQuantity = cartDTO.getProducts().getOrDefault(id, 0);
        if(currentCartQuantity >= 1)
        {
            cartDTO.decreaseProduct(id);
        }else
        {
            redirectAttributes.addFlashAttribute("msgError", "S? l??ng s?n ph?m không âm");

        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/increase/{id}")
    public String increaseQuantity(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent())
        {
            int currentCartQuantity = cartDTO.getProducts().getOrDefault(id, 0);
            int currentQuantity = product.get().getQuantity();
            if(currentQuantity > currentCartQuantity)
            {
                cartDTO.addProduct(id);
            }
            else{
                redirectAttributes.addFlashAttribute("msgError", "S? l??ng s?n ph?m " + currentQuantity + "còn l?i");
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeCartItem(@PathVariable Long id) {
        cartDTO.removeProduct(id);
        return "redirect:/cart";
    }
}
